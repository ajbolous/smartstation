from flask import request, jsonify
from .. import app
from ..database.database import users, jsonify  

@app.route('/login', methods=['POST'])
def login():
    jObj = request.get_json()
    userid = jObj["userid"]
    password = jObj["password"]
    user = users.getById(userid)
    if user and user.id == userid and user.password == password:
        return jsonify({'success': True,'message':'Login successful'})
    return jsonify({'success': False,'message' : 'Wrong username or password'})

@app.route('/getUsers')
def getUsers():
    '''
        Get all users in the database return array of users
    '''
    pass

@app.route('/assignUserToRoute')
def assignUserToRoute():
    '''
        Assign a User (Driver) to route
        input: userid, routeid, startStation
        return successs or fail
    '''
    pass