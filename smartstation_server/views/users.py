from flask import request, jsonify
from .. import app
from ..database.database_handler import dbHandler

@app.route('/login')
def login():
    userid = request.args.get('userid')
    password = request.args.get('password')
    user = dbHandler.users.find(userid)
    if user and user.userid == userid and user.password == password:
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