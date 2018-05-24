from flask import request, jsonify
from .. import app
from ..database import database as db


@app.route('/login', methods=['POST'])
def login():
    jObj = request.get_json()
    userid = jObj["userid"]
    password = jObj["password"]
    user = db.users.getById(userid)
    if user and user.id == userid and user.password == password:
        return jsonify({'success': True, 'message': 'Login successful'})
    return jsonify({'success': False, 'message': 'Wrong username or password'})


@app.route('/users/getUsers')
def getUsers():
    return jsonify(db.users.all())


@app.route('/users/assignUserToRoute')
def assignUserToRoute():
    '''
        Assign a User (Driver) to route
        input: userid, routeid, startStation
        return successs or fail
    '''
    pass
