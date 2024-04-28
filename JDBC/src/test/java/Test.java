import com.hbsi.jdbc.student.entity.StudentEntity;
import com.hbsi.jdbc.teacher.entity.TeacherEntity;

import java.security.SecureClassLoader;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {

//        Class.forName("ClassForNameClassLoader");
//        ClassLoader loader = ClassLoader.getSystemClassLoader();
//        loader.loadClass("ClassLoaderTest");
//
//        ArrayList<Object> es = new ArrayList<>();
        printBean(new TeacherEntity());
        printBean(new StudentEntity());
        TestInterFace<? extends StudentEntity> t = new TestInterFaceImpl<>();
        TestInterFace<? super StudentEntity> t1 = new TestInterFaceImpl<>();
    }

    public static <T> void printBean(T t) {
        System.out.println(t);
    }
}
