from flask import request, jsonify
from .. import app
from ..database.database_handler import dbHandler

@app.route('/login')
def login():
    userid = request.args.get('userid')
    password = request.args.get('password')

    for user in dbHandler.getUsers():
        if user.userid == userid and user.password == password:
            return jsonify({
                "success": True,
                "message": "Login successful"
            })
    return jsonify({
        "success": False,
        "message": "Wrong username or password"
    })

# Get all users in the database return array of users
@app.route('/getUsers')
def getUsers():
    return jsonify([user.toJson() for user in dbHandler.getUsers()])
    
@app.route('/assignUserToRoute')
def assignUserToRoute():
    '''
        Assign a User (Driver) to route
        input: userid, routeid, startStation
        return successs or fail
    '''    
    pass