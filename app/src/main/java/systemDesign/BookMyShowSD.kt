package systemDesign

import java.util.*
import kotlin.collections.HashMap

class BookMyShow {
}

/** Requirements
1.System should be able to list the cities where theatres are located
2.Each theatre can have multiple screens and each screen will play one show at a time
3.Each movie will have multiple shows
4.System should allow user to search a movie by title, genre,language, release date etc
5.Once user select a movie, the system should display the theatres playing the movie and it's shows
6.User should be able to select show of a particular theatre and book a ticket
7.The system should show the seating arrangements to the user of a cinema hall and user should be able to select seat(s)
8.The user should be able to distinguish between available and booked seats.
9.USer should be able to do payment with different modes
10. User should be able to Rate/Comment, watch trailers etc
 */

/** Actors
1.Customer
2.Admin
3.Server
 */

/** UseCase
1.Guest can browse around the app but can't book the ticket
2.Registered user can browse around and book a ticket
3.Admin can add/remove shows/movies
4.Server can trigger notification for booking,  email/message ticket
5.Server will provide the list if movie, theatres, shows, bookings of a user etc
6.Server will provide trailers, comments, ratings etc
 */

class BMSService{
    private val theatres:List<Theatre>
        get() = theatres

    fun getMovies(city:String):List<Movie> = emptyList()
    fun getTheatres(city:String):List<Movie> = emptyList()
}

class Theatre(id:Int, name:String, address: TheatreAddress, screens:List<TheatreScreen>){
     fun getMovies(dates:List<Date>):HashMap<Date, List<Movie>> = HashMap()
     fun removie(movieId:Int){}
     fun getShows(dates:List<Date>):HashMap<Date, List<Show>> = HashMap()
     fun setShows(dates:List<Date>, movie: Movie):HashMap<Date, List<Show>> = HashMap()
}

class TheatreAddress(street:String, city:String, pinCode:Int, lat:Double, long:Double)

class TheatreScreen(id: Int, name:String, totalSeats:Int, shows:List<Show>)

class Show(id: Int, movie: Movie, startTime:Date, endTime:Date,theatre: Theatre, seats:List<Seat>)

class Seat(id: Int,type:SeatType, status:SeatStatus, price:Double)

enum class SeatType{
    STANDARD,
    GOLD,
    RECLINER
}

enum class SeatStatus{
    BOOKED,
    AVAILABLE,
    NOT_AVAILABLE
}

class Movie(id: Int, name:String, durationInMin:Int, releaseDate:Date, genre:Genre, language:String, showsToCity:HashMap<String, List<Show>>){
    fun getComments():List<Comment> = emptyList()
    fun getRatings():List<Rating> = emptyList()
}

enum class Genre{
    THRILLER,
    SCI_FI,
    HORROR,
    DRAMA,
    FICTION,
    ROMANTIC,
    ACTION
}

open class User(userId:Int, searchObj:MovieSearch)

open class SystemMember(userId: Int, searchObj: MovieSearch, account:BMSUserAccount, userName:String, email:String, address:BMSUserAddress):User(userId, searchObj)

class BMSMember(userId: Int, searchObj: MovieSearch, account:BMSUserAccount, userName:String, email:String, address:BMSUserAddress):
    SystemMember(userId, searchObj, account, userName, email, address){
        fun makeBooking(booking:MovieBooking){}
        fun getBookings(bookingId:Int){}
        fun getTrailer(trailer: Trailer){}
        fun getComments(movieId: Int){}
        fun getTrailer(movieId: Int){}
        fun postRating(rating: Rating){}
        fun postComment(comment: Comment){}
}

class BMSAdmin(userId: Int, searchObj: MovieSearch, account:BMSUserAccount, userName:String, email:String, address:BMSUserAddress):
    SystemMember(userId, searchObj, account, userName, email, address){
    fun addMovieToTheatre(movie:Movie, theatre: Theatre){}
    fun addMovieToTheatres(movie:Movie, theatres: List<Theatre>){}
    fun removeMovie(movieId:Int, theatre: Theatre){}
    fun addShow(show: Show){}
    fun removeShow(showId: Int){}
    fun addTrailer(trailer: Trailer){}
    fun removeTrailer(trailreId: Int){}
}

class Trailer(id: Int, durationInMin:Int, movie:Movie)

class Rating(id: Int, stars:Int, movie:Movie)

class Comment(id: Int, description:Int, movie:Movie)

class BMSUserAddress(street:String, city:String, pinCode:Int)

class BMSUserAccount(userID:String, password:String, status:BMSUserAccountStatus)

enum class BMSUserAccountStatus{
    ACTIVE,
    INACTIVE,
    BLOCKED,
    CLOSED
}

class MovieSearch{
    fun searchMovieByTitle(title:String):List<Movie> = emptyList()
    fun searchMovieByReleaseDate(date:Date):List<Movie> = emptyList()
    fun searchMovieByGenre(genre:Genre):List<Movie> = emptyList()
    fun searchMovieByLanguage(language:String):List<Movie> = emptyList()
}

class MovieBooking(bookingId:Int, bookingDate:Date, bookedBy:User, show:Show, screen:TheatreScreen, status:BMSBookingStatus, totalAmount:Double){
    fun makePayment(payment:BMSPayment):Boolean = true
}

class BMSPayment(txnId:Int, total:Double, paymentStatus:BMSPaymentStatus, date:Date)

enum class BMSPaymentStatus{
    SUCCESSFUL,
    PENDING,
    DECLINED,
    REFUNDED,
    CANCELLED
}

enum class BMSBookingStatus{
    INITIATED,
    PENDING,
    CONFIRMED,
    CACELLED
}