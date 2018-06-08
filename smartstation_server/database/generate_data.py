from ..model.user import User
from ..model.bus import Bus
from ..model.client import Client

import json
import random

def generateUserData():
    users = []
    for i in range(1,10):
        u = User()
        u.firstName = 'User ' + str(i)
        u.lastName = 'User ' + str(i)
        u.age = 20+i
        u.id = 'user' + str(i)
        u.password = u.id
        u.operator = 'Eged'
        u.email = u.id + '@gmail.com'
        users.append(u)
    with open('smartstation_server/database/data/users.json', 'w',encoding='UTF-8') as file:
        json.dump([user.toJson() for user in users], file)

def generateClientsData():
    clients = []
    for i in range(1,16):
        c = Client()
        if (i < 6):
            c.id = 'client' + str(i)
            c.email = c.id + '@gmail.com'
            c.password = c.id
            clients.append(c)
        elif (i > 10):
            c.id = 'client' + str(i)
            for j in range(10):
                c.CombinedCard = c.CombinedCard + str(random.randint(1,9))
            clients.append(c)
        else:
            c.id = 'client' + str(i)
            for j in range(16):
                c.CreditCard = c.CreditCard + str(random.randint(1,9))
            for j in range(3):
                c.CVV = c.CVV + str(random.randint(1,9))
            clients.append(c)
    with open('smartstation_server/database/data/clients.json', 'w',encoding='UTF-8') as file:
        json.dump([client.toJson() for client in clients], file)

def generateBuses():
    buses = []
    for i in range(1,13):
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