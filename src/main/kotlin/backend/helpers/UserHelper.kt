package backend.helpers

import backend.api.models.users.randomUser
import backend.controllers.Controllers
import backend.api.extension.ResponseExt.getAsObject

class UserHelper: Controllers() {
     fun ensureAmountOfUsers(target: Int) {
        val existingUsers = users.getAllUsers()
            .getAsObject()
            .size

        val neededUsers = (target - existingUsers).coerceAtLeast(0)
        repeat(neededUsers){
            users.createUser(randomUser()).getAsObject()
        }
    }

}