import mysql.connector
import matplotlib.pyplot as plt
import smtplib
# Connect to database
conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="Yashvi2108",
    database="monitoring_system"
)

cursor = conn.cursor()

# Get all logs
cursor.execute("SELECT level FROM logs")
data = cursor.fetchall()

# Count logs
info = 0
error = 0
warning = 0

for row in data:
    if row[0] == "INFO":
        info += 1
    elif row[0] == "ERROR":
        error += 1
    elif row[0] == "WARNING":
        warning += 1

# Print result
print("Total Logs:", len(data))
print("INFO:", info)
print("ERROR:", error)
print("WARNING:", warning)

# Create chart
labels = ["INFO", "ERROR", "WARNING"]
values = [info, error, warning]

plt.bar(labels, values)
plt.title("Log Analysis")
plt.xlabel("Log Type")
plt.ylabel("Count")
# Alert system
if error > 5:
    print("⚠ ALERT: Too many errors! System unstable!")
elif warning > 5:
    print("⚠ WARNING: High warning level!")
else:
    print("System is stable")
import matplotlib.pyplot as plt

labels = ["INFO", "ERROR", "WARNING"]
values = [info, error, warning]

plt.bar(labels, values)
plt.title("Log Analysis Chart")
plt.xlabel("Log Type")
plt.ylabel("Count")


plt.show()

plt.show()