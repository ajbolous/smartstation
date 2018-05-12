from flask import Flask, jsonify, request

app = Flask(__name__)
app.debug = True  

import view_routes
import view_stations
import view_users

app.run()
