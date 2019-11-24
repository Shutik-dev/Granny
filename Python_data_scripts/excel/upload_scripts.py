import xlrd
import pymysql
pymysql.install_as_MySQLdb()

# Open the workbook and define the worksheet
book = xlrd.open_workbook("Granny.xls")
recipes_sheet	= book.sheet_by_name("recipes")
points_sheet	= book.sheet_by_name("points")
ings_sheet		= book.sheet_by_name("ings")
tools_sheet		= book.sheet_by_name("tools")

# Establish a MySQL connection
database = pymysql.connect(host="localhost", user="root", passwd="root", db="gdb")

# Get the cursor, which is used to traverse the database, line by line
cursor = database.cursor()

# Create tables
create_recipes_query = """CREATE TABLE IF NOT EXISTS recipes(
_id INT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(300),
img VARCHAR(300),
points_am INT,
ings_am INT,
tools_am INT,
likes INT,
tod VARCHAR(30),
descr TEXT);"""
create_points_query = """CREATE TABLE IF NOT EXISTS points(
_id INT PRIMARY KEY AUTO_INCREMENT,
rec_id INT,
num VARCHAR(300),
text TEXT,
timer INT);"""
create_ings_query = """CREATE TABLE IF NOT EXISTS ings(
_id INT PRIMARY KEY AUTO_INCREMENT,
rec_id INT,
name TEXT,
dose INT,
type VARCHAR(100));"""
create_tools_query = """CREATE TABLE IF NOT EXISTS tools(
_id INT PRIMARY KEY AUTO_INCREMENT,
rec_id INT,
name TEXT);"""
database.query(create_recipes_query)
database.query(create_points_query)
database.query(create_ings_query)
database.query(create_tools_query)

# Create the INSERT INTO sql query
add_recipes_query	= """INSERT INTO recipes (title, img, points_am, ings_am, tools_am, likes, tod, descr) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)"""
add_points_query	= """INSERT INTO points (rec_id, num, text, timer) VALUES (%s, %s, %s, %s)"""
add_ings_query		= """INSERT INTO ings (rec_id, name, dose, type) VALUES (%s, %s, %s, %s)"""
add_tools_query		= """INSERT INTO tools (rec_id, name) VALUES (%s, %s)"""


# Create a For loop to iterate through each row in the XLS file, starting at row 2 to skip the headers
for r in range(1, recipes_sheet.nrows):
		title		= recipes_sheet.cell(r, 0).value
		img			= recipes_sheet.cell(r, 1).value
		points_am	= recipes_sheet.cell(r, 2).value
		ings_am		= recipes_sheet.cell(r, 3).value
		tools_am	= recipes_sheet.cell(r, 4).value
		likes		= recipes_sheet.cell(r, 5).value
		tod			= recipes_sheet.cell(r, 6).value
		descr		= recipes_sheet.cell(r, 7).value

		# Assign values from each row
		values = (title, img, points_am, ings_am, tools_am, likes, tod, descr)

		# Execute sql Query
		cursor.execute(add_recipes_query, values)

for r in range(1, points_sheet.nrows):
		rec_id		= points_sheet.cell(r, 0).value
		num			= points_sheet.cell(r, 1).value
		text		= points_sheet.cell(r, 2).value
		timer		= points_sheet.cell(r, 3).value
		if timer == '': timer = None

		# Assign values from each row
		values = (rec_id, num, text, timer)

		# Execute sql Query
		cursor.execute(add_points_query, values)

for r in range(1, ings_sheet.nrows):
		rec_id		= ings_sheet.cell(r, 0).value
		name		= ings_sheet.cell(r, 1).value
		dose		= ings_sheet.cell(r, 2).value
		type		= ings_sheet.cell(r, 3).value

		# Assign values from each row
		values = (rec_id, name, dose, type)

		# Execute sql Query
		cursor.execute(add_ings_query, values)

for r in range(1, tools_sheet.nrows):
		rec_id		= tools_sheet.cell(r, 0).value
		name		= tools_sheet.cell(r, 1).value

		# Assign values from each row
		values = (rec_id, name)

		# Execute sql Query
		cursor.execute(add_tools_query, values)

# Close the cursor
cursor.close()

# Commit the transaction
database.commit()

# Close the database connection
database.close()

# Print results
print("---------------------------")
print("All Done! Bye, for now.")
print("---------------------------")