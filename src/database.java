public interface database {
    String dir = System.getProperty("user.dir");

    String db_url_user = "jdbc:sqlite:%s\\src\\db\\user_info.db".formatted(dir);
    void creat_db();
    void db_update();

}
