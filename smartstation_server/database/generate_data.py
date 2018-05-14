from ..model.user import User
from ..model.bus import Bus

import json

def generateUserData():
    users = []
    for i in range(1,10):
        u = User()
        u.firstName = 'User ' + str(i)
        u.lastName = 'Manyak'
        u.age = 20+i
        u.userid = 'user' + str(i)
        u.password = u.userid
        users.append(u)
    with open('smartstation_server/database/data/users.json', 'w',encoding='UTF-8') as file:
        json.dump([user.toJson() for user in users], file)

def generateBuses():
    buses = []
    for i in range(1,10):
        b = Bus()
        b.id = 'bus' + str(i)
        b.isActive = False
        b.licensePlate = '123-bus-' + str(i)
        b.routeId = 0
        b.driverId = 0
        b.lat = 0
        b.lng = 0
        buses.append(b)
    with open('smartstation_server/database/data/buses.json', 'w',encoding='UTF-8') as file:
        json.dump([bus.toJson() for bus in buses], file)