from flask import Flask, request, jsonify
from pyngrok import ngrok
import pickle
import numpy as np

model = pickle.load(open('model.pkl','rb'))

app = Flask(__name__)

@app.route('/')
def home():
    return "Hello world"

@app.route('/predict', methods=['POST'])
def predict():
    age = request.form.get('age')
    hypertension = request.form.get('hypertension')
    heartDisease = request.form.get('heartDisease')
    everMarried = request.form.get('everMarried')
    residenceType = request.form.get('residenceType')
    avgGlucoseLevel = request.form.get('avgGlucoseLevel')
    bmi = request.form.get('bmi')
    genderMale = request.form.get('genderMale')
    genderOther = request.form.get('genderOther')
    workTypeNeverWorked = request.form.get('workTypeNeverWorked')
    workTypePrivate = request.form.get('workTypePrivate')
    workTypeSelfEmployed = request.form.get('workTypeSelfEmployed')
    workTypeChildren = request.form.get('workTypeChildren')
    smokingStatusFormerlySmoked = request.form.get('smokingStatusFormerlySmoked')
    smokingStatusNeverSmoked = request.form.get('smokingStatusNeverSmoked')
    smokingStatusSmokes = request.form.get('smokingStatusSmokes')
    bmiCatIdeal = request.form.get('bmiCatIdeal')
    bmiCatOverweight = request.form.get('bmiCatOverweight')
    bmiCatObesity = request.form.get('bmiCatObesity')
    ageCatTeens = request.form.get('ageCatTeens')
    ageCatAdults = request.form.get('ageCatAdults')
    ageCatMidAdults = request.form.get('ageCatMidAdults')
    ageCatElderly = request.form.get('ageCatElderly')
    glucoseCatNormal = request.form.get('glucoseCatNormal')
    glucoseCatHigh = request.form.get('glucoseCatHigh')
    glucoseCatVeryHigh = request.form.get('glucoseCatVeryHigh')

    # result = {'age':age, 'hypertension':hypertension, 'heartDisease':heartDisease, 'everMarried':everMarried, 'residenceType':residenceType,
    #           'avgGlucoseLevel':avgGlucoseLevel, 'bmi':bmi, 'genderMale':genderMale, 'genderOther':genderOther, 'workTypeNeverWorked':workTypeNeverWorked,
    #           'workTypePrivate':workTypePrivate, 'workTypeSelfEmployed':workTypeSelfEmployed, 'workTypeChildren':workTypeChildren,
    #           'smokingStatusFormerlySmoked':smokingStatusFormerlySmoked, 'smokingStatusNeverSmoked':smokingStatusNeverSmoked,
    #           'smokingStatusSmokes':smokingStatusSmokes, 'bmiCatIdeal':bmiCatIdeal, 'bmiCatOverweight':bmiCatOverweight, 'bmiCatObesity':bmiCatObesity,
    #           'ageCatTeens':ageCatTeens, 'ageCatAdults':ageCatAdults, 'ageCatMidAdults':ageCatMidAdults, 'ageCatElderly':ageCatElderly, 'glucoseCatNormal':glucoseCatNormal,
    #           'glucoseCatHigh':glucoseCatHigh, 'glucoseCatVeryHigh':glucoseCatVeryHigh}
    
    # return jsonify(result)

    input_query = np.array([[age, hypertension, heartDisease, everMarried, residenceType, avgGlucoseLevel, bmi, genderMale, genderOther, workTypeNeverWorked,
              workTypePrivate, workTypeSelfEmployed, workTypeChildren, smokingStatusFormerlySmoked, smokingStatusNeverSmoked, smokingStatusSmokes, bmiCatIdeal,
              bmiCatOverweight, bmiCatObesity, ageCatTeens, ageCatAdults, ageCatMidAdults, ageCatElderly, glucoseCatNormal, glucoseCatHigh, glucoseCatVeryHigh]])

    result = model.predict(input_query)[0]

    return jsonify({'Stroke':str(result)})

url = ngrok.connect(5000)
print('Public URL:', url)

if __name__ == '__main__':
    #app.run(debug=True)
    app.run(port=5000)