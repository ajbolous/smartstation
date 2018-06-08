from flask import request, jsonify
from .. import app
from ..database import database as db

@app.route('/payments/checkPayPal', methods=['POST'])
def checkPayPal():
    jObj = request.get_json()
    email = jObj["email"]
    password = jObj["password"]
    client = db.clients.find(lambda obj: obj.email == email)
    if client and client[0].email == email and client[0].password == password:
        return jsonify({'success': True, 'message': 'payment successful'})
    return jsonify({'success': False, 'message': 'Wrong email or password'})

@app.route('/payments/checkCreditCard', methods=['POST'])
def checkCreditCard():
    jObj = request.get_json()
    clientid = jObj["clientid"]
    CreditCard = jObj["CreditCard"]
    ExpDate = jObj["ExpDate"]
    CVV = jObj["CVV"]
    client = db.clients.getById(clientid)
    if client and client.id == clientid and client.CreditCard == CreditCard and client.ExpDate == ExpDate and client.CVV == CVV:
        return jsonify({'success': True, 'message': 'payment successful'})
    return jsonify({'success': False, 'message': 'Wrong informations'})

@app.route('/payments/checkCombiendCard', methods=['POST'])
def checkCombiendCard():
    jObj = request.get_json()
    clientid = jObj["clientid"]
    CombinedCard = jObj["CombinedCard"]
    client = db.clients.getById(clientid)
    if client and client.id == clientid and client.CombinedCard == CombinedCard:
        return jsonify({'success': True, 'message': 'payment successful'})
    return jsonify({'success': False, 'message': 'Wrong id or Card Number'})