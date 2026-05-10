package backend.helpers

import backend.api.endpoints.exstension.Exstension.Companion.getAsObject
import backend.controllers.Controllers

class UserHelper: Controllers() {
     fun ensureAmountOfUsers(target: Int) {
        val existingUsers = user.getAllUsers()
            .getAsObject()
            .size

        val neededUsers = (target - existingUsers).coerceAtLeast(0)
        repeat(neededUsers){
            user.createUser(randomUser()).getAsObject()
        }
    }

}