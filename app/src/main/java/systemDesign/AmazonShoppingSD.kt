package systemDesign

import java.lang.reflect.Type
import java.util.*

class AmazonShoppingSD {
}

/** Requirements
1.Users should be able to search/browse the products by name, category etc
2.Only registered users can buy/sell items
3.User should be able to add products to sell
3.User should be able to add/edit/remove products from shopping cart
4.User should be able to checkout the shopping cart and buy items
5.User should be able to do online payment.
6.The system should send notifications to ordered user for any change in the status of the order
7. User should also be able to travk his shipment and order status
8. User can rate and add review to the product
 */

/** Actors
1.Buyer
2.Seller
3.Server
 */

/** UseCase
1.Seller will add Products
2.Buyer will buy products
3.Buyer will add items to shopping cart
4.Buyer will add rating and review to a product
5.Server will list down all the products
6.Server will notify users for the change in their Order status, shipping status etc
 */

open class AmazonCustomer(customerId:Int, searchObj:AmazonSearch, shoppingCart:AmazonShoppingCart){
    fun getShoppingCart(customerId:Int):AmazonShoppingCart? = null
    fun addItemToShoppingCart(item:AmazonCartItem){}
    fun removeItemFromShoppingCart(itemId:Int){}
    fun modifyItemOfAShoppingCart(item: AmazonCartItem){}
}

class AmazonGuest(customerId:Int, searchObj:AmazonSearch, shoppingCart:AmazonShoppingCart):AmazonCustomer(customerId, searchObj, shoppingCart){
    fun createNewAccount(){}
}

open class AmazonMember(customerId:Int, searchObj:AmazonSearch,account:AmazonAccount, shoppingCart:AmazonShoppingCart):AmazonCustomer(customerId, searchObj, shoppingCart)

class AmazonSeller(customerId:Int, searchObj:AmazonSearch, account:AmazonAccount, shoppingCart: AmazonShoppingCart):AmazonMember(customerId, searchObj, account, shoppingCart){
    fun addProduct(product:AmazonProduct){}
}

class AmazonBuyer(customerId:Int, searchObj:AmazonSearch, account:AmazonAccount, orderObj:AmazonOrder, shoppingCart: AmazonShoppingCart):AmazonMember(customerId, searchObj, account, shoppingCart){
    fun addReview(review:AmazonProductReview){}
    fun placeOrder(shoppingCart:AmazonShoppingCart):AmazonOrderStatus = AmazonOrderStatus.PLACED
}

class AmazonAccount(userName:String, password:String, email:String, phone:String, addresses:List<AmazonAddress>, accountStatus:AmazonAccountStatus)

enum class AmazonAccountStatus{
    ACTIVE,IN_ACTIVE,BLOCKED
}

class AmazonShoppingCart(var items:MutableList<AmazonCartItem>, cartValue:Double){
    fun addCartItem(item:AmazonCartItem){
        items.add(item)
    }
    fun deleteCartItem(item:AmazonCartItem){
        items.remove(item)
    }
    fun updateCartItem(item:AmazonCartItem){
        val oldItem = items.find { item.cartItemId == it.cartItemId }
        items.remove(oldItem)
        items.add(item)
    }
    fun checkoutItems(items:AmazonCartItem){}
}

class AmazonAddress(pinCode:Int, street:String, city:String, state:String, country:String, phone:String, email:String)

class AmazonCartItem(val cartItemId:Int, quantity:Int, product:AmazonProduct, itemValue:Double)

class AmazonProduct(productId:Int, description:String, title:String, category:AmazonProductCategory, seller:AmazonSeller, cost:Double){
    fun getRatingAndReviews(productId: Int):AmazonProductReview? = null
}

enum class AmazonProductCategory{
    ELECTRONICS,FASHION,GROCERIES,HOME_AND_KITCHEN,BOOKS
}

class AmazonProductReview(id:Int, rating:Int, reviewComment:String, buyer:AmazonBuyer, productId: Int)

class AmazonSearch{
    fun searchByName(name:String):List<AmazonProduct> = emptyList()
    fun searchByCategory(category:AmazonProductCategory):List<AmazonProduct> = emptyList()
}
class AmazonOrder(orderId:Int, items:List<AmazonCartItem>, orderValue:Double, buyer:AmazonBuyer, orderDate:Date, orderLogs:List<AmazonOrderLog>, notificationObject:AmazonNotificationService){
    fun placeOrder():AmazonOrderStatus = AmazonOrderStatus.PLACED
    fun trackOrder():AmazonOrderStatus = AmazonOrderStatus.READY_TO_BE_SHIPPED
    fun makePayment(paymentType:AmazonPaymentType):AmazonPaymentStatus = AmazonPaymentStatus.INITIATED
}
enum class AmazonPaymentStatus{
    SUCCESS, DECLINED, FAILED, IN_PROGRESS, INITIATED, REFUND_INITIATED, REFUNDED
}
enum class AmazonPaymentType{
    DEBIT_CARD, CREDIT_CARD, EMI, COD
}
enum class AmazonOrderStatus{
    PLACED, PACKED, READY_TO_BE_SHIPPED, SHIPPED, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
}
class AmazonOrderLog(orderStatus: AmazonOrderStatus, createdDate:Date, orderDetail:String)

abstract class AmazonNotificationAttribute{
    abstract fun getSenderInfo():String
}

class AmazonMessageAttribute:AmazonNotificationAttribute(){
    override fun getSenderInfo():String {
        return ConfigUtils.SENDER_PHONE
    }
}

class AmazonEmailAttribute:AmazonNotificationAttribute(){
    override fun getSenderInfo():String {
        return ConfigUtils.SENDER_EMAIL
    }
}


abstract class AmazonNotificationClient{
    abstract fun sendNotification(domain: NotificationDomain, messageAttribute:AmazonMessageAttribute)
}

class AmazonEmailNotification:AmazonNotificationClient(){
    override fun sendNotification(domain: NotificationDomain, messageAttribute: AmazonMessageAttribute) {
       //send email notification
    }
}

class AmazonSMSNotification:AmazonNotificationClient(){
    override fun sendNotification(domain: NotificationDomain, messageAttribute: AmazonMessageAttribute) {
        //send sms notification
    }
}

class AmazonWhatsAppNotification:AmazonNotificationClient(){
    override fun sendNotification(domain: NotificationDomain, messageAttribute: AmazonMessageAttribute) {
        //send whats app notification
    }
}

class AmazonNotificationService(){
    fun sendNotification(domain:NotificationDomain, client:AmazonNotificationClient, messageAttribute:AmazonMessageAttribute){
        client.sendNotification(domain, messageAttribute)
    }
}

class NotificationDomain(id:Int, type:NotificationType, user:User)

enum class NotificationType {
    SMS,EMAIL,WHATSAPP
}

object ConfigUtils{
    const val SENDER_EMAIL = "amazon-services@amazon.in"
    const val SENDER_PHONE = "1234567890"
}