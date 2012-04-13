'''
Created on 2012-04-12

@author: Gooble
'''
import re

if __name__ == "__main__":
    string = "Creating the Sample Databases\n"\
            +"Before you can run this project, you must create at a minimum the depot_development database, as described below. You can also create the test and production databases if desired.\n"\
            +"To Create the Depot Databases:\n"\
            +"Open a command window.\n"\
            +"If it has not already been started, start the MySQL database server.\n"\
            +"Type the following command to create the development database and press Enter.\n"\
            +"mysqladmin -u root -p create depot_development\n"\
            +"Note: If the root user does not have a required password, omit the -p argument.\n"\
            +"(Optional) Repeat the command to create the depot_test and depot_production databases.\n"\
            +"Configuring The Database Environment\n"
            
    for line in string.split("\n"):
        if re.match("Configuring.*", line):
            print (line)
    
