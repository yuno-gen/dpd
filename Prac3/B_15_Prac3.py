
from asyncio.windows_events import NULL
import pymysql
import datetime


card_no = input("Enter the card number: ")
pin = input("Enter the card pin: ")
withdrawal_amt = float(input("Enter amount to withdraw: "))

today = datetime.datetime.today()
#paris database
# print("Connecting........ Paris instance")
conn1 = pymysql.connect(
    host='bcpcsxss1zmqtkfvg39q-mysql.services.clever-cloud.com',
    user='uybx7xvspaed5ycl', 
    password = "Fg35TbunMQywjCacUWEb",
    database='bcpcsxss1zmqtkfvg39q',
    charset='utf8mb4',
    cursorclass=pymysql.cursors.DictCursor
    )
cur1 = conn1.cursor()
cur1.execute("select @@version")



#montreal database
# print("Connecting........ Montreal Instance")
conn2 = pymysql.connect(
    host='bqvq6lck6dm10vfsutvk-mysql.services.clever-cloud.com',
    user='ug7fbb0xexnrfoza', 
    password = "qhXvGeJmuvKQLikmmkkJ",
    database='bqvq6lck6dm10vfsutvk',
    charset='utf8mb4',
    cursorclass=pymysql.cursors.DictCursor
    )
cur = conn2.cursor()
# cur.execute("select @@version")
# cur.execute("INSERT INTO employee_personal(empno ,partitionkey, empnm, age, qualification, mobile, emailid) VALUES(%s, %s, %s, %s, %s, %s, %s)", (emp_number, 2, name, age, qualification, mobile, email_id))
# cur.execute("select * from employee_personal")
# for row in cur.fetchall():
#     print(row)

cur1.execute("Select * from creditcards where cardno=%s and pin=%s",(card_no, pin))
curr_balance = 0
bank = ""
for row in cur1.fetchall():
    # print(row)
    accNumber = row['mappedaccno']
    if row['translimit']>=withdrawal_amt:
        count = cur.execute("Select * from citibank where accno=%s", (accNumber))
        values = cur.fetchone()
        if count>0:
            if values['balance']>0:
                curr_balance = values['balance'] - withdrawal_amt
                cur.execute("Update citibank SET balance= %s", (curr_balance))
            
        else:
            count = cur.execute("Select * from scbank where accno=%s", (accNumber))
            values = cur.fetchone()
            if count>0:
                if values['balance']>0:
                    curr_balance = values['balance'] - withdrawal_amt
                    cur.execute("Update scbank set balance= %s", (curr_balance))

        cur.execute("Insert into cardtranslog(transdt, accno, amount) Values(%s, %s, %s)", (today, accNumber, withdrawal_amt))
        cur1.execute("Insert into paymentlog(cardno, transdt, purpose, paymentamount) Values(%s, %s, %s, %s)", (card_no, str(today), "Shopping", str(withdrawal_amt)))
        print("Transaction Successfull from: "+ bank)
        print("Account Number: ", accNumber)
        print("Amount withdrawal: ", withdrawal_amt)
        print("Current Balance: ", curr_balance)

        cur.execute("Select * from cardtranslog where accno=%s", accNumber)
        print("Card Log:")
        for row in cur.fetchall():
            print(row)
        cur1.execute("Select * from paymentlog where cardno=%s", card_no)
        print("Payment Log:")
        for row in cur1.fetchall():
            print(row)
    else: print("Transaction limit exceeded")


conn1.commit()
# To close the connection
conn1.close()

conn2.commit()
# To close the connection
conn2.close()