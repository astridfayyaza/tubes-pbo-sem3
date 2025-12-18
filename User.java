public abstract class User {

    protected int user_id;
    protected String nama;
    protected String username;
    protected String password;

    public enum Role { ADMIN, KASIR }
    protected Role role;

    public User(int user_id, String nama, String username,
                String password, Role role) {
        this.user_id = user_id;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // PENGECEKAN USERNAME & PASSWORD (WAJIB)
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername)
            && this.password.equals(inputPassword);
    }

    public String getNama() {
        return nama;
    }

    public Role getRole() {
        return role;
    }
}
