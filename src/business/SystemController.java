package business;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	public static final SystemController INSTANCE = new SystemController();

	private SystemController() {
	}

	public void login(String id, String password) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if (!map.containsKey(id)) {
			throw new LibrarySystemException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();

		System.out.println(" user :" + map.get("id") + " passwrd " + passwordFound);

		if (!passwordFound.equals(password)) {
			throw new LibrarySystemException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
	}

	@Override
	public List<String[]> getMemberCheckoutEntries(String memberId) throws LibrarySystemException {
		LibraryMember member = searchMember(memberId);
		if (member == null) {
			throw new LibrarySystemException("Member with with id '" + memberId + "' does not exist");
		}
		List<CheckoutRecordEntry> checkoutBooks = member.getCheckoutRecord().getCheckoutRecordEntries();
		List<String[]> records = new ArrayList<>();
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		for (CheckoutRecordEntry ch : checkoutBooks) {
			String[] recs = new String[] { memberId, ch.getBookCopy().getBook().getIsbn(),
					Integer.toString(ch.getBookCopy().getCopyNum()),
					simpleDateFormat.format((ch.getCheckoutDate().getTime())),
					simpleDateFormat.format((ch.getDueDate().getTime())), };
//            System.out.println(Arrays.toString(recs) + " |||||||||||");
			records.add(recs);
		}
		return records;
	}

	@Override
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		Book book = da.searchBook(isbn);
		List<String[]> records = getMemberCheckoutEntries(memberId);

		if (book == null) {
			throw new LibrarySystemException("Book with ISBN '" + isbn + "' not found.");
		}
		LibraryMember member = searchMember(memberId);
		if (member == null) {
			throw new LibrarySystemException("Library member with ID '" + memberId + "' not found.");
		}
		if (!book.isAvailable()) {
			throw new LibrarySystemException(
					"There are no available copies for book with ID '" + isbn + "' to checkout.");
		}

		for (String[] rec : records) {
			if (memberId.equals(rec[0]) && isbn.equals(rec[1]))
				throw new LibrarySystemException(
						"Book ISBN " + rec[1] + " has been carried with the Member ID " + rec[0] + " before!");
//            System.out.println(rec[1]);
		}

		Calendar calCurDate = Calendar.getInstance(); // Using today's date
		calCurDate.setTime(new Date());
		Calendar dueCalDate = Calendar.getInstance();
		dueCalDate.setTime(new Date()); // Using today's date
		dueCalDate.add(Calendar.DATE, book.getMaxCheckoutLength());
		CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(calCurDate, dueCalDate,
				book.getNextAvailableCopy());
		member.getCheckoutRecord().addCheckOutEntry(checkoutRecordEntry);
		da.updateMember(member);
		updateBook(book);
	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public void addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors)
			throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		Book storedBook = da.searchBook(isbn);
		if (storedBook != null) {
			throw new LibrarySystemException("Book with ISBN " + isbn + " already exists");
		}
		Book book = new Book(isbn, title, maxCheckoutLength, authors);
		da.saveNewBook(book);
	}

	@Override
	public void addMember(String id, String firstName, String lastName, String cell, String street, String city,
			String state, String zip) throws LibrarySystemException {
		if (id.length() == 0 || firstName.length() == 0 || lastName.length() == 0 || cell.length() == 0
				|| street.length() == 0 || city.length() == 0 || state.length() == 0 || zip.length() == 0) {
			throw new LibrarySystemException("All fields must be non-empty");
		}
		Address address = new Address(street, city, state, zip);
		if (searchMember(id) != null) {
			throw new LibrarySystemException("Library Member with ID " + id + " already exists");
		}
		DataAccess da = new DataAccessFacade();
		LibraryMember member = new LibraryMember(id, firstName, lastName, cell, address);
		da.saveMember(member);
	}

	@Override
	public void updateBook(Book book) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		if (book == null) {
			throw new LibrarySystemException("Book is null");
		}
		da.updateBook(book);
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> value = new ArrayList<>();
		value.addAll(da.readBooksMap().keySet());
		return value;
	}

	@Override
	public Book getBookById(String isbn) {
		DataAccess da = new DataAccessFacade();
		return da.searchBook(isbn);
	}

	@Override
	public User getUser(String userId) {
		DataAccess da = new DataAccessFacade();
		return da.searchUser(userId);
	}

	@Override
	public LibraryMember searchMember(String memberId) {
		DataAccess da = new DataAccessFacade();
		return da.searchMember(memberId);
	}

	public String[] isBookOverDue(String isbn) {
		LocalDate today = LocalDate.now();
		CheckoutRecordEntry check = null;
		LibraryMember libraryMember = null;
		DataAccessFacade da = new DataAccessFacade();
		HashMap<String, LibraryMember> mems = da.readMemberMap();

		for (Map.Entry<String, LibraryMember> member : mems.entrySet()) {
			System.out.println("xxxx");
			List<CheckoutRecordEntry> checkoutEntries = member.getValue().getCheckoutRecord()
					.getCheckoutRecordEntries();

			for (CheckoutRecordEntry ce : checkoutEntries) {
				if (today.isAfter((convertToLocalDateViaInstant(ce.getDueDate().getTime())))
						&& !ce.getBookCopy().isAvailable()) {
					// all overdue books
					System.out.println("bbb");

					Book bb = ce.getBookCopy().getBook();
					if (bb.getIsbn().equals(isbn)) {
						check = ce;
						libraryMember = member.getValue();
						break;
					}
				}
			}

		}

		if (check == null)
			return new String[] { "EMPTY", "EMPTY", "EMPTY", "EMPTY", "EMPTY" };

		Book b = check.getBookCopy().getBook();
		return new String[] { b.getIsbn(), b.getTitle(), String.valueOf(b.getNumCopies()),
				libraryMember.getFirstName() + " " + libraryMember.getLastName(), check.getDueDate().toString() };
	}

	public static LocalDate convertToLocalDateViaInstant(java.util.Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	@Override
	public List<String[]> getAllBookDetails() {
		List<String[]> bookDisplayList = new ArrayList<>();
		List<String> bookIsbns = allBookIds();
		List<Book> bookList = new ArrayList<>();
		bookIsbns.forEach((isbn) -> {
			bookList.add(getBookById(isbn));
		});
		for (Book book : bookList) {
			String[] list = new String[] { book.getIsbn(), book.getTitle() };
			bookDisplayList.add(list);
		}
		return bookDisplayList;
	}

}
