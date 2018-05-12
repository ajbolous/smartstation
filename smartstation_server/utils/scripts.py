from ..model.user import User
import json

def generateUserData():
    users = []
    for i in range(1,10):
        u = User()
        u.firstName = "User " + str(i)
        u.lastName = "Manyak"
        u.age = 20+i
        u.userid = "user" + str(i)
        u.password = u.userid
        users.append(u)
    with open("./data/users.json", "w",encoding="UTF-8") as file:
        json.dump([user.toJson() for user in users], file)

generateUserData()