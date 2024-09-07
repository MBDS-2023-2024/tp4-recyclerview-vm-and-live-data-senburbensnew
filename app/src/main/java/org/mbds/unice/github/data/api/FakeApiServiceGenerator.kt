package org.mbds.unice.github.data.api

import org.mbds.unice.github.data.model.User
import java.util.*

object FakeApiServiceGenerator {
    @JvmField
    var FAKE_USERS = mutableListOf(
        User("001", "Jake", "https://xsgames.co/randomusers/avatar.php?g=male", true),
        User("002", "Paul", "https://xsgames.co/randomusers/avatar.php?g=male", true),
        User("003", "Phil", "https://xsgames.co/randomusers/avatar.php?g=male", true),
        User("004", "Guillaume", "https://xsgames.co/randomusers/avatar.php?g=male", true),
        User("005", "Francis", "https://xsgames.co/randomusers/avatar.php?g=male", true),
        User("006", "George", "https://xsgames.co/randomusers/avatar.php?g=male", true),
        User("007", "Louis", "https://xsgames.co/randomusers/avatar.php?g=male", true),
        User("008", "Mateo", "https://xsgames.co/randomusers/avatar.php?g=male", true),
        User("009", "April", "https://xsgames.co/randomusers/avatar.php?g=female", true),
        User("010", "Louise", "https://xsgames.co/randomusers/avatar.php?g=female", true),
        User("011", "Elodie", "https://xsgames.co/randomusers/avatar.php?g=female", true),
        User("012", "Helene", "https://xsgames.co/randomusers/avatar.php?g=female", true),
        User("013", "Fanny", "https://xsgames.co/randomusers/avatar.php?g=female", true),
        User("014", "Laura", "https://xsgames.co/randomusers/avatar.php?g=female", true),
        User("015", "Gertrude", "https://xsgames.co/randomusers/avatar.php?g=female", true),
        User("016", "Chloé", "https://xsgames.co/randomusers/avatar.php?g=female", true),
        User("017", "April", "https://xsgames.co/randomusers/avatar.php?g=female", true),
        User("018", "Marie", "https://xsgames.co/randomusers/avatar.php?g=female", true),
        User("019", "Henri", "https://xsgames.co/randomusers/avatar.php?g=male", true),
        User("020", "Rémi", "https://xsgames.co/randomusers/avatar.php?g=male", true)
    )

    @JvmField
    var FAKE_USERS_RANDOM = Arrays.asList(
        User("021", "Lea", "https://xsgames.co/randomusers/avatar.php?g=female", true),
        User("022", "Geoffrey", "https://xsgames.co/randomusers/avatar.php?g=male", true),
        User("023", "Simon", "https://xsgames.co/randomusers/avatar.php?g=male", true),
        User("024", "André", "https://xsgames.co/randomusers/avatar.php?g=male", true),
        User("025", "Leopold", "https://xsgames.co/randomusers/avatar.php?g=male", true)
    )
}