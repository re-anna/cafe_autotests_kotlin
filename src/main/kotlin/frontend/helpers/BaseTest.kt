package frontend.helpers

import backend.controllers.Controllers
import infra.junit.UsersForTestExt
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(UsersForTestExt::class)
open class BaseTest: Controllers ()