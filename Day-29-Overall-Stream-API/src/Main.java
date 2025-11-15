import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "Engineering", "Senior Developer", Employee.Status.ACTIVE, 2000.0),
                new Employee(2, "Bob", "Engineering", "Junior Developer", Employee.Status.ACTIVE, 800.0),
                new Employee(3, "Charlie", "HR", "Recruiter", Employee.Status.ACTIVE, 800.0),
                new Employee(4, "David", "Engineering", "Senior Developer", Employee.Status.INACTIVE, 2000.0), // Bị loại (INACTIVE)
                new Employee(5, "Eve", "Sales", "Manager", Employee.Status.ACTIVE, 400.0),
                new Employee(6, "Frank", "Engineering", "Senior Developer", Employee.Status.ACTIVE, 2500.0)
        );

        //Yêu cầu: "Tôi muốn biết tổng quỹ lương phải trả cho tất cả Senior Developer đang Active (đang làm việc) trong bộ phận Engineering."
        double totalOld = calculateSalary_OldWay(employees);
        System.out.println("Tổng lương (Cách cũ): " + totalOld);

        double totalNew = calculateSalary_StreamWay(employees);
        System.out.println("Tổng lương (Stream): " + totalNew);
    }

    public static double calculateSalary_OldWay(List<Employee> employees) {
        double totalSalary = 0.0;

        // Lặp qua tất cả nhân viên
        for (Employee emp : employees) {

            // Bắt đầu các khối if lồng nhau...
            if ("Engineering".equals(emp.getDepartment())) {

                if ("Senior Developer".equals(emp.getTitle())) {

                    if (Employee.Status.ACTIVE.equals(emp.getStatus())) {

                        // Cuối cùng, logic nghiệp vụ
                        totalSalary += emp.getSalary();
                    }
                }
            }
        }
        return totalSalary;
    }

    public static double calculateSalary_StreamWay(List<Employee> employees) {

        return employees.stream() // 1. Lấy luồng dữ liệu

                // 2. Lọc (Filter) - Gộp các điều kiện
                // Thay vì 3 'if', ta dùng 3 'filter'
                .filter(emp -> "Engineering".equals(emp.getDepartment()))
                .filter(emp -> "Senior Developer".equals(emp.getTitle()))
                .filter(emp -> Employee.Status.ACTIVE.equals(emp.getStatus()))

                // Dòng chảy lúc này chỉ còn: [Employee("Alice"), Employee("Frank")]

                // 3. Biến đổi (Map)
                // Chúng ta không cần đối tượng Employee nữa, chỉ cần lương (double)
                .mapToDouble(Employee::getSalary)
                // (Dùng mapToDouble hiệu quả hơn .map() cho kiểu số)

                // Dòng chảy lúc này: [2000.0, 2500.0]

                // 4. Tổng hợp (Reduce)
                .sum(); // Tính tổng
    }
}