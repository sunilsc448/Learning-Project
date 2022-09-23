package systemDesign
import java.util.*

class HotelManagement {}

/** Requirements
1.Room can be of different types - Deluxe/Luxury/Standard
2.Guest should be able to search and book available rooms
3.System should be able to fetch available rooms, rooms booked by a Customer etc
4.System should be able to allow to cancel the book room 24rs prior to check-in date
5.System should be able to send notifications
6.Guest can add room services, order food items
7.Guest should be able to make payments - with different modes(Cash, Card etc)
8.Admin can add/edit/remove room
9.Receptionist should be able to check-in/check-out Guest
10.Housekeeping management - provide service, add log
 */

/** Actors
1.Admin
2.Guest
3.Receptionist
4.HouseKeeper
5. Server
 */

/** UseCase
1.Guest can search for available rooms
2.Guest can book a room
3.Guest can cancel the booked room
4.HouseKeeper can maintain housekeeping logs
5.Admin can add a room, edit a room, delete a room
6.Receptionist can add room charges
7.Receptionist can book a room
8.Receptionist can check-in/check-out
9.Server will provide a list of available rooms
10.Server can provide a list of bookings of a Guest
11.Server can send notifications regarding booking, near check-in date
 */

//Refer Use case/UML diagram in Book

class Hotel(val name:String, val id:Int, val location:systemDesign.Location, val rooms:List<Room>)

class Location(pinCode:Int, street:String, city:String, country:String, lat:Double, long:Double)

class Room(val number:String, val type:RoomType, val status:RoomStatus, val keys:List<RoomKey>, val houseKeepingLogs:MutableList<HouseKeepingLog>)

enum class RoomType{
    STANDARD,
    DELUXE,
    SUITE
}

enum class RoomStatus{
    BOOKED,
    RESERVED,
    OCCUPIED,
    UNAVAILABLE,
    SERVICE_IN_PROGRESS
}

class RoomKey(val id:Int, val barCode:String, val issuedAt:Date, val isActive:Boolean, val isMaster:Boolean)

class HouseKeepingLog(val description:String, val houseKeeper:HouseKeeper, val startDate: Date, val duration:Float){
    fun addRoom(room: Room){
        val houseKeepingLog = HouseKeepingLog(description, houseKeeper, startDate, duration)
        room.houseKeepingLogs.add(houseKeepingLog)
    }
}

class HouseKeeper(name: String, accountDetail: Account): Person(name, accountDetail){
    fun getHousekeepings(room: Room):List<HouseKeepingLog>{
        return room.houseKeepingLogs
    }
}

open class Person(name: String, accountDetail:Account)

class Account(val username:String, val password:String, status:AccountStatus)

enum class AccountStatus{
    ACTIVE,
    CLOSED,
    BLOCKED
}

class Guest(name: String, accountDetail:Account, val searchObject:Search, val bookingObject:HotelBook):Person(name, accountDetail){
    fun getBookings():List<RoomBooking> = emptyList()
}

class Admin(name: String, accountDetail: Account):Person(name, accountDetail){
    fun addRoom(room: Room){}
    fun editRoom(room: Room){}
    fun deleteRoom(roomNumber: Int){}
}

class Receptionist(name: String, accountDetail: Account, val searchObject: Search, val bookingObject: HotelBook):Person(name, accountDetail){
    fun checkInGuest(guestInfo: Guest, roomBooking: RoomBooking){}
    fun checkoutGuest(guestInfo: Guest, roomBooking: RoomBooking){}
}

class Search(val roomType: RoomType, val startDate: Date, val duration:Int, val guests:List<Guest>, val rooms:List<Room>){
    fun searchRoom():List<Room>{
        return emptyList()
    }
}

class HotelBook{
    fun createBooking(guestInfo:Guest):RoomBooking = RoomBooking(BookingIDGenerator.getBookingID(), guestInfo.searchObject.startDate, guestInfo.searchObject.duration,
                                                                guestInfo.searchObject.rooms, guestInfo.searchObject.guests, RoomCharges())
    fun cancelBooking(guestInfo:Guest, bookingId:Int):RoomBooking{
        val bookings = guestInfo.getBookings()
        val booking = bookings.first {it.id == bookingId}
        booking.status = BookingStatus.CANCELLED
        return booking
    }
}

class RoomBooking(val id:Int, startDate: Date, duration: Int, rooms:List<Room>, guests:List<Guest>,
                  val roomCharges:BaseRoomCharges, var status:BookingStatus = BookingStatus.IN_PROGRESS)

enum class BookingStatus{
    IN_PROGRESS,
    CONFIRMED,
    FAILED,
    CANCELLED
}

object BookingIDGenerator{
    private var bookingIDCounter = 0
    fun getBookingID():Int{
        return ++bookingIDCounter
    }
}

abstract class BaseRoomCharges{
    abstract fun getCost():Double
}

class RoomCharges:BaseRoomCharges(){
    private var cost = 100.0
    override fun getCost(): Double {
        return cost
    }
    fun setCost(cost:Double) {
        this.cost = cost
    }
}

class RoomServiceCharges(var roomCharges:RoomCharges):BaseRoomCharges(){
    private val cost = getServiceCharges()

    private fun getServiceCharges(): Double {
        //based on services consumed by Guest
        return 12.0
    }

    override fun getCost(): Double {
        roomCharges.setCost(roomCharges.getCost() + getServiceCharges())
        return roomCharges.getCost()
    }
}




















