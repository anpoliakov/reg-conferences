package constants;

public class SQLConstants {
    public static final String SELECT_USER = "SELECT * FROM users WHERE login=? and password=?";
    public static final String INSERT_USER = "insert into users(login, email, firstName, secondName, password) values(?,?,?,?,?)";
    public static final String FOUND_LOGIN = "select id from users where login=?";

    public static final String SELECT_CONF = "SELECT * FROM conferences ";
    public static final String WHERE_ALL = "";
    public static final String WHERE_TODAY = "where date = NOW()::DATE";
    public static final String WHERE_TOMORROW = "where date = CURRENT_DATE + INTERVAL '1 DAY'";
    public static final String WHERE_SOON ="where date > CURRENT_DATE + INTERVAL '1 DAY'";
    public static final String WHERE_PAST ="where date < CURRENT_DATE";
    public static final String WHERE_CONF_BY_USER = "WHERE idUser =?";

    public static final String SELECT_EVENTS = "SELECT events.id, events.name, events.time FROM events INNER JOIN conferences ON events.idConf = conferences.id WHERE events.idConf = ? ORDER BY events.time";

    public static final String ID_LABEL = "id";
    public static final String LOGIN_LABEL = "login";
    public static final String FNAME_LABEL = "firstName";
    public static final String SNAME_LABEL = "secondName";
    public static final String EMAIL_LABEL = "email";
    public static final String NAME_LABEL = "name";
    public static final String TITLE_LABEL = "title";
    public static final String DESCR_LABEL = "descr";
    public static final String PLACE_LABEL = "place";
    public static final String DATE_LABEL = "date";
    public static final String TIME_LABEL = "time";

    public static final String INSERT_CONF = "INSERT INTO conferences (idUser, title, descr, place, date) VALUES (?,?,?,?,?)";
    public static final String INSERT_EVENTS = "INSERT INTO events (idConf, name, time) VALUES (?, ?, ?)";
    public static final String DELETE_CONF = "DELETE FROM conferences WHERE id = ? AND idUser = ?";
}
