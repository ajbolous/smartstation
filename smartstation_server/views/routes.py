from flask import request, jsonify
from .. import app
from ..database.database import routes, stations, users, buses
from ..model.algorithms import calcShortestPaths
from ..model.route import Route
from ..model.start import Start

@app.route('/routes/getRoutes')
def getRoutes():
    return jsonify(routes.all())

@app.route('/routes/getShortestPath')
def getShortestPath():
    source = request.args.get('source')
    dest = request.args.get('dest')

    path = calcShortestPaths(stations.all(), routes.all(), source, dest)
    print(path)
    return jsonify(path)

@app.route('/routes/getRoutesFromStation')
def getRoutesFromStation():
    stationId = request.args.get('stationId')
    _routes = []
    for route in routes.all():
        for stop in route.stops:
            if stop.stationId == stationId:
                _routes.append(route)
                break
    return jsonify(_routes)

@app.route('/routes/getAvailableRoutes')
def getAvailableRoutes():
    userid = request.args.get('userid')
    user = users.getById(userid)
    if user:
        company = user.company
        
        _routes = []
        for route in routes.find(lambda obj: obj.company == company):
            s = Start()
            s.number = route.number
            idFirst = route.stops[-len(route.stops)].stationId
            idLast = route.stops[-1].stationId
            for station in stations.all():
                if station.id == idFirst:
                    s.firstStation = station.description
                elif station.id == idLast:
                    s.lastStation = station.description    
            _routes.append(s)
        return jsonify(_routes)    
    return jsonify({'fail': False,'message' : 'ERROR'})
  
