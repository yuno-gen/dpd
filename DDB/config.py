import pymysql
# def emp_inputs():
while(True):
    print("Enter the required details as promted: ")

    print("Employee number: ")
    emp_number = input()

    print("Name: ")
    name = input()

    print("Location: ")
    location = input()

    print("Department: ")
    department = input()
    # partion_key = 1 for paris
    # partion_key = 2 for montreal

    print("Post: ")
    post = input()

    print("Salary: ")
    salary = input()

    age = input("Age: ")
    qualification = input("Qualification: ")
    mobile = input("Mobile number: ")
    email_id = input("Email id: ")

    #Paris connection
    print("Connecting........ Paris instance")
    conn1 = pymysql.connect(
        host='bcpcsxss1zmqtkfvg39q-mysql.services.clever-cloud.com',
        user='uybx7xvspaed5ycl', 
        password = "Fg35TbunMQywjCacUWEb",
        database='bcpcsxss1zmqtkfvg39q',
        charset='utf8mb4',
        cursorclass=pymysql.cursors.DictCursor
        )
    cur = conn1.cursor()
    cur.execute("select @@version")
    cur.execute("INSERT INTO employee_official(empno ,partitionkey, empnm, location, dept, post, salary) VALUES(%s, %s, %s, %s, %s, %s, %s)", (emp_number, 1, name, location, department, post, salary))
    cur.execute("select * from employee_official")
    for row in cur.fetchall():
        print(row)
    conn1.commit()
    # To close the connection
    conn1.close()


    #Montreal Connection
    print("Connecting........ Montreal Instance")
    conn2 = pymysql.connect(
        host='bqvq6lck6dm10vfsutvk-mysql.services.clever-cloud.com',
        user='ug7fbb0xexnrfoza', 
        password = "qhXvGeJmuvKQLikmmkkJ",
        database='bqvq6lck6dm10vfsutvk',
        charset='utf8mb4',
        cursorclass=pymysql.cursors.DictCursor
        )
    cur = conn2.cursor()
    cur.execute("select @@version")
    cur.execute("INSERT INTO employee_personal(empno ,partitionkey, empnm, age, qualification, mobile, emailid) VALUES(%s, %s, %s, %s, %s, %s, %s)", (emp_number, 2, name, age, qualification, mobile, email_id))
    cur.execute("select * from employee_personal")
    for row in cur.fetchall():
        print(row)
    conn2.commit()
    # To close the connection
    conn2.close()
    
    print("Data entered. Enter 1 to continue else enter 0.")
    if(int(input())==0):
        break


