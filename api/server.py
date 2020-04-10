from flask import Flask, request, make_response, jsonify, send_file, send_from_directory
from flask_cors import CORS
import db

app = Flask(__name__) 
CORS(app)


@app.route('/addPost', methods = ['POST'])
def addPostFun():
  print(request.json)
  return jsonify(db.addPost(request.json))

@app.route('/getAllPosts', methods=['GET'])
def getAllPostsFun(): 
    return jsonify(db.getAllPosts())

@app.route('/getAllTruePosts', methods=['GET'])
def getAllTruePostsFun(): 
    return jsonify(db.getAllTruePosts())

@app.route('/getAllFakePosts', methods=['GET'])
def getAllFakePostsFun(): 
    return jsonify(db.getAllFakePosts())


@app.route('/getPostUsingURL', methods = ['GET'])
def getPostUsingURLFun():
  return jsonify(db.getPostUsingURL(request.json))

@app.route('/fakePost', methods = ['POST'])
def fakePostFun():
  print(request.json)
  return jsonify(db.fakePost(request.json))

@app.route('/addUser', methods = ['POST'])
def addUserFun():
  print(request.json)
  return jsonify(db.addUser(request.json))

@app.route('/getAllUsers', methods=['GET'])
def getAllUsersFun(): 
    return jsonify(db.getAllUsers())


@app.route('/getAllForests', methods=['GET'])
def getAllForestsFun(): 
    return jsonify(db.getAllForests())

@app.route('/getAllAffectedUsers', methods=['GET'])
def getAllAffectedUsersFun(): 
    return jsonify(db.getAllAffectedUsers())

@app.route('/getForestUsingBSSID', methods = ['POST'])
def getForestUsingBSSIDFun():
  print(request.json)
  return jsonify(db.getForestUsingBSSID(request.json))


@app.route('/getAllMayBeAffectedUsers', methods=['GET'])
def getAllMayBeAffectedUsersFun(): 
    return jsonify(db.getAllMayBeAffectedUsers())

@app.route('/deleteAllUsers', methods=['GET'])
def deleteAllUsersFun(): 
    return jsonify(db.deleteAllUsers())

@app.route('/addPeer', methods = ['POST'])
def addPeerFun():
  return jsonify(db.addPeer(request.json))

@app.route('/markUser', methods = ['POST'])
def markUserFun():
  return jsonify(db.markUser(request.json))

@app.route('/updateGPS', methods = ['POST'])
def updateGPSFun():
  return jsonify(db.updateGPS(request.json))


@app.route('/getUserUsingAdhar', methods = ['POST'])
def getUserUsingAdharFun():
  return jsonify(db.getUserUsingAdhar(request.json))

@app.route('/getUserUsingBSSID', methods = ['POST'])
def getUserUsingBSSIDFun():
  return jsonify(db.getUserUsingBSSID(request.json))

if __name__ == '__main__': 
  app.run(debug = True) 
