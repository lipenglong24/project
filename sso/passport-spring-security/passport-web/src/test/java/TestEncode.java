import org.junit.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * passport
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
public class TestEncode {
    @Test
    public void testBcrypt() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("123456");
        System.out.println(result);
        String dbResult = encoder.encode(result);
        System.out.println(dbResult);
        System.out.println(encoder.matches(result, dbResult));
        result = encoder.encode("123456");
        System.out.println(result);
        System.out.println(encoder.matches(result, dbResult));


//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("123456")
//                .roles("ADMIN")
//                .build();
//        System.out.println(user.getPassword());
    }
}
