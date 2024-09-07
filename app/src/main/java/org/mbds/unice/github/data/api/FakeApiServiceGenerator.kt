package org.mbds.unice.github.data.api

import org.mbds.unice.github.data.model.User
import java.util.*

object FakeApiServiceGenerator {

    private fun randomCreationDate(): Date {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val randomYear = (currentYear - (0..10).random()) // Random year within the last 10 years
        calendar.set(randomYear, (0..11).random(), (1..28).random()) // Random month and day
        return calendar.time
    }

    @JvmField
    var FAKE_USERS = mutableListOf(
        User("001", "Jake", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true),
        User("002", "Paul", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true),
        User("003", "Phil", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true),
        User("004", "Guillaume", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true),
        User("005", "Francis", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true),
        User("006", "George", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true),
        User("007", "Louis", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true),
        User("008", "Mateo", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true),
        User("009", "April", "https://xsgames.co/randomusers/avatar.php?g=female", randomCreationDate(), true),
        User("010", "Louise", "https://xsgames.co/randomusers/avatar.php?g=female", randomCreationDate(), true),
        User("011", "Elodie", "https://xsgames.co/randomusers/avatar.php?g=female", randomCreationDate(), true),
        User("012", "Helene", "https://xsgames.co/randomusers/avatar.php?g=female", randomCreationDate(), true),
        User("013", "Fanny", "https://xsgames.co/randomusers/avatar.php?g=female", randomCreationDate(), true),
        User("014", "Laura", "https://xsgames.co/randomusers/avatar.php?g=female", randomCreationDate(), true),
        User("015", "Gertrude", "https://xsgames.co/randomusers/avatar.php?g=female", randomCreationDate(), true),
        User("016", "Chloé", "https://xsgames.co/randomusers/avatar.php?g=female", randomCreationDate(), true),
        User("017", "April", "https://xsgames.co/randomusers/avatar.php?g=female", randomCreationDate(), true),
        User("018", "Marie", "https://xsgames.co/randomusers/avatar.php?g=female", randomCreationDate(), true),
        User("019", "Henri", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true),
        User("020", "Rémi", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true)
    )

    @JvmField
    var FAKE_USERS_RANDOM = listOf(
        User("021", "Lea", "https://xsgames.co/randomusers/avatar.php?g=female", randomCreationDate(), true),
        User("022", "Geoffrey", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true),
        User("023", "Simon", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true),
        User("024", "André", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true),
        User("025", "Leopold", "https://xsgames.co/randomusers/avatar.php?g=male", randomCreationDate(), true)
    )
}