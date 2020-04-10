import pymongo
import networkx as nx
import matplotlib.pyplot as plt
import random
import string

client = pymongo.MongoClient("mongodb://localhost:27017/")
db = client["mydatabase"]
post = db["post"]
# graphs = db["graphs"]
# forest = db["forest"]

# add post,  name, url, text, author, source
def addPost(data):
  # data["peer"] = []  
  # data["val"] = 0
  data["fake"] = False
  x = post.insert_one(data)
  return 1

def fakePost(data):
  post.update_one({"url": data["url"]}, { "$set": { "fake": True } })
  return 1

def getAllPosts():
  res = list()
  for x in post.find():
    del x["_id"]
    res.append(x)
  return res

def getAllTruePosts():
  res = list()
  for x in post.find({"fake" : False}):
    del x["_id"]
    res.append(x)
  return res

def getAllFakePosts():
  res = list()
  for x in post.find({"fake" : True}):
    del x["_id"]
    res.append(x)
  return res 


def getPostUsingURL(data):
  try:
    res = post.find_one({"url" : data["url"]})
    del res["_id"]
    return res
  except:
    return None




def addPeer(data):
  #bssid1 is of user and bssid2 is of peer 
  a = user.find_one({"bssid" : data["bssid1"]})["peer"]
  a.append(data["bssid2"])
  newvalues = { "$set": { "peer": a } }
  user.update_one({"bssid": data["bssid1"]}, newvalues)


  o = user.find_one({"bssid" : data["bssid2"]})
  a = o["peer"]
  a.append(data["bssid1"])
  newvalues = { "$set": { "peer": a } }
  user.update_one({"bssid": data["bssid2"]}, newvalues)

  if(o["val"] == 1):              # new found peer has got virus
    user.update_one({"bssid": data["bssid1"]}, { "$set": { "val": 2 } })  # so now user may also be affected
    getForest(data["bssid2"])          # remake the forest graph
    return o["val"]
  elif(o["val"] == 2):         # new found peer maybe affected with virus
    user.update_one({"bssid": data["bssid1"]}, { "$set": { "val": 2 } })  # so now user may also be affected
    getForest(data["bssid2"])          # remake the forest graph
    return o["val"]
  return 0 

def updateGPS(data):
  try:
    user.update_one({"bssid": data["bssid"]}, { "$set": { "gps": [data["lat"], data["long"]] } })
    return 1
  except:
    return 0



# ************************************* not to be used in android app *************************************    


def getForest(databssid):
  my_graph = nx.Graph() 
   
  edges = []
  
  graphName = randomString(20) + ".png"

  q = list()
  q.append(databssid)
  sett = set()
  sett.add(databssid)
  forestlis = []

  while(len(q) > 0):
    s = q.pop(0)
    a = user.find_one({"bssid" : s})["val"]
    
    # if user is not affected one, then he should marked as maybe affected person i.e val = 2
    if(a == 0):
      user.update_one({"bssid" : s}, { "$set": { "val": 2 } })   # peer maybe affected
    
    user.update_one({"bssid" : s}, {"$set" : {"graphName" : graphName}})   # update the associated graph for the peer

    for x in user.find_one({"bssid" : s})["peer"]:
      if x not in sett:
        q.append(x)
        sett.add(x)
        edges.append((s, x))
    forestlis.append(s)
  
  my_graph.add_edges_from(edges)
  nx.draw(my_graph, with_labels=True, font_weight="bold")
  plt.savefig(graphName)

  forest.insert_one({"graphName" : graphName, "data" : forestlis})

  return forestlis


def getAllUsers():
  res = list()
  for x in user.find():
    del x["_id"]
    res.append(x)
  return res

def getAllForests():
  res = list()
  for x in forest.find():
    del x["_id"]
    res.append(x)
  return res

def getForestUsingBSSID(data):
  res = user.find_one({"bssid" : data["bssid"]})
  result = []
  del res["_id"]
  result.append(res)
  sett = set()
  sett.add(res["bssid"])
  res = forest.find_one({"graphName" : res["graphName"]})
  print(res)
  for x in res["data"]:
    if x not in sett:
      r = user.find_one({"bssid" : x})
      del r["_id"]
      result.append(r)
      sett.add(r["bssid"])


  return result 

def getAllAffectedUsers():
  res = list()
  for x in user.find({"val" : 1}):
    del x["_id"]
    res.append(x)
  return res


def getAllMayBeAffectedUsers():
  res = list()
  for x in user.find({"val" : 2}):
    del x["_id"]
    res.append(x)
  return res

def deleteAllUsers():
  user.delete_many({})
  return 1

def deleteAllForest():
  forest.delete_many({})
  return 1

def getUserUsingAdhar(data):
  try:
    # print('****\n', data, '\n*****\n')
    res = user.find_one({"adharno" : data["adharno"]})
    del res["_id"]
    return res
  except:
    return None

def getUserUsingBSSID(data):
  try:
    res = user.find_one({"bssid" : data["bssid"]})
    del res["_id"]
    return res
  except:
    return None
                    # new found peer is not affected with virus


def randomString(stringLength=10):
  letters = string.ascii_lowercase
  return "".join(random.choice(letters) for i in range(stringLength))


def randoman(stringLength=12):
  letters = '0123456789'
  return "".join(random.choice(letters) for i in range(stringLength))


def randomac(stringLength=12):
  letters = '0123456789abcdef'
  return "".join(random.choice(letters) for i in range(stringLength))
    

def markUser(data):
  user.update_one({"bssid": data["bssid"]}, { "$set": { "val": 1 } })
  getForest(data["bssid"])
  return 1


# s = []
# deleteAllForest()
# deleteAllUsers()

authors = [
  'Aditi Musunur',
   'Advitiya Sujeet',
   'Alagesan Poduri',
   'Amrish Ilyas',
   'Aprativirya Seshan',
   'Asvathama Ponnada',
   'Avantas Ghosal',
   'Avidosa Vaisakhi',
   'Barsati Sandipa',
  'Debasis Sundhararajan',
  'Devasru Subramanyan',
  'Dharmadhrt Ramila',
  'Dhritiman Salim',
  'Gopa Trilochana',
  'Hardeep Suksma',
  'Jayadev Mitali',
  'Jitendra Choudhary',
  'Kalyanavata Veerender',
  'Naveen Tikaram',
  'Vijai Sritharan']


# for x in range(0, 20):
#   data = dict()
#   data["url"] = authors[x] + ".com"
#   data["text"] = "text"
#   data["name"] = authors[x] + " post"
#   data["author"] = authors[x]
#   data["date"] = "10th April"
#   data["source"] = "whatsapp"
#   addPost(data)

# addPeer({"bssid1" : s[0], "bssid2" : s[1]})

# addPeer({"bssid1" : s[1], "bssid2" : s[2]})

# addPeer({"bssid1" : s[2], "bssid2" : s[3]})

# addPeer({"bssid1" : s[0], "bssid2" : s[1]})

# addPeer({"bssid1" : s[1], "bssid2" : s[3]})

# s.pop(0)
# s.pop(1)
# s.pop(2)
# s.pop(3)

# for x in range(4, 8):
#   x1 = random.choice(s)
#   addPeer({"bssid1" : str(x1), "bssid2" : str(random.choice(s))})
#   addPeer({"bssid1" : str(x1), "bssid2" : str(random.choice(s))})
#   addPeer({"bssid1" : str(x1), "bssid2" : str(random.choice(s))})



# for x in getAllUsers():
#   print(x)




# markUser({"bssid" : 4})

# for x in getAllUsers():
#   print(x)



  
# print(getAllUsers())
# print(getAllUsers())

# print(db.bsonsize(db.collection.find( {user :"user"})))


# mylist = [
#   { "name": "Amy", "address": "Apple st 652"},
#   { "name": "Hannah", "address": "Mountain 21"},
#   { "name": "Michael", "address": "Valley 345", "peer" : [1, 2]},
#   { "name": "Sandy", "address": "Ocean blvd 2"},
#   { "name": "Betty", "address": "Green Grass 1"},
#   { "name": "Richard", "address": "Sky st 331"},
#   { "name": "Susan", "address": "One way 98"},
#   { "name": "Vicky", "address": "Yellow Garden 2"},
#   { "name": "Ben", "address": "Park Lane 38", "peer" : [1, 2]},
#   { "name": "William", "address": "Central st 954"},
#   { "name": "Chuck", "address": "Main Road 989"},
#   { "name": "Viola", "address": "Sideway 1633", "peer" : [1, 2]},
#   { "name": "Viola1", "address": "Sideway 1634", "peer" : [1, 2]},
#   { "name": "Viola2", "address": "Sideway 1634", "peer" : [1, 2]}
# ]


# x = user.insert_many(mylist)

# myquery = {"name" : "Viola2" }
# a = user.find_one({"name" : "Viola2"})["peer"]
# a.append(4)
# # print(a)

# # print(user.find_one({"name" : "Voila1"})["peer"])
# # a = user.find_one({"name" : "Ben"})["peer"].push(5)


# newvalues = { "$set": { "peer": a } }

# user.update_one(myquery, newvalues)

# print(user.find_one({"name" : "Viola2"}))

# print(col.find_one())

# x = user.insert_many(mylist)

# print list of the _id values of the inserted documents:
# print(x.inserted_ids) 

# print(user.find_one({"name" : "Viola"}))