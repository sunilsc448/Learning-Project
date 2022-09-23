package systemDesign

import pojos.User
import java.util.*

class LibraryManagement {}

/**Requirements
 1. Library member should be able to Search Book by Title, Genre, Published date,Publisher, Author etc
 2. Each Book will have Unique number, Rack number which will help to locate
 3. There can be multiple copies of a Book
 4. Library member should be able to Checkout(available)/Reserve a Book(not available)/Renew a Book
 5. There should be a maximum limit for number of books and days a member can hold
 6. Admin can add/remove a Book
 7. Librarian can issue a Book
 8. System should be able to Penalise in case of delay in Returning the Book
 9. System should be able to send Notifications for members on due date and for the ones who did not return the book on time.
 10. Each book/Member card should have a barcode and system should be able to read Barcode.
 */

/** Actors
 1. Librarian
 2. Library member
 3. Admin
 4. Server
 */

/** Use case
 1. Add/Remove/Edit a  Book item
 2. Search Books
 3. Send Notifications
 4. Issue a book
 5. Reserve a book
 6. Renew a book
 7. Return a book
*/

class Library(name:String, location:Address, books:List<HotelBook>)

class Address(pinCode:Int, street:String, city:String, state:String, lat:Double, long: Double)

class Book(id:Int, title:String, authors:List<Author>, type:BookType)

class BookItem(val barcode:String, val publishDate:Date, val rackLocation:Rack, val status:BookStatus, val format:BookFormat, val issueDate:Date)

open class LibPerson(val firstName:String, val secondName:String)

class Author(firstName: String, secondName: String):LibPerson(firstName, secondName){
  fun getPublishedBooks():List<Book> = emptyList()
}

class LibraryMember(firstName: String, lastName: String, totalBooksChecked:Int, searchObj:LibrarySearch, bookObj:LibraryBooking):LibPerson(firstName, lastName)

class Librarian(firstName: String, lastName: String, searchObj:LibrarySearch, bookObj:LibraryBooking):LibPerson(firstName, lastName){
  fun addBookItem(item: BookItem){}
  fun removeBookItem(item: BookItem){}
  fun editBookItem(barcode: String){}
}

class LibrarySearch{
  fun getBooksByTitle():List<BookItem> = emptyList()
  fun getBooksByName():List<BookItem> = emptyList()
  fun getBooksByType():List<BookItem> = emptyList()
  fun getBooksByPublication():List<BookItem> = emptyList()
}

class LibraryBooking(fineService: FineService){
  fun getReservationDetail(item: BookItem, user: LibraryMember) = BookReservation(item, item.issueDate, user, ReservationStatus.ISSUED)
  fun updateReservationDetail(item: BookItem, user: LibraryMember) = BookReservation(item, item.issueDate, user, ReservationStatus.ISSUED)
  fun issueBook(item: BookItem, user: LibraryMember) = BookLending(item, item.issueDate, user)
  fun renewBook(item: BookItem, user: LibraryMember) = BookLending(item, item.issueDate, user)
  fun returnBook(item: BookItem, user: LibraryMember) {}
}

open class BookLending(bookItem: BookItem, startDate: Date, user: LibraryMember)

class BookReservation(bookItem: BookItem, startDate: Date, user: LibraryMember,val status:ReservationStatus):BookLending(bookItem, startDate, user)

class FineService(){
 fun calculateFine(item: BookItem, user: LibraryMember, days:Int):Fine = Fine(days)
}

class Fine(days: Int)

enum class ReservationStatus {
 RESERVED,
 ISSUED
}

class Rack(id:Int, number:Int)

enum class BookType{
  THRILLER,
  ACADEMICS,
  SCIENCE,
  SCI_FI,
  NOVEL,
  DRAMA,
  ROMANCE
}

enum class BookStatus{
  UNAVAILABLE,
  RESERVED,
  AVAILABLE
}

enum class BookFormat{
 HARDCOVER,
 PAPERBACK,
 JOURNAL,
 NEWSPAPER
}


