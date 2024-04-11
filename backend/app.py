from flask import Flask, request, jsonify
import tensorflow as tf
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
from flask_cors import CORS
import numpy as np
import librosa
import json

app = Flask(__name__)
CORS(app, resources={r"/*": {"origins": "http://localhost:3000"}})

# Load the text model
with open("model_text.json", "r") as json_file:
    loaded_model_json_1 = json_file.read()

text_model = tf.keras.models.model_from_json(loaded_model_json_1)
text_model.load_weights('model_weights_text.h5')


# Load the audio model
with open('model.json', 'r') as json_file:
    loaded_model_json_2 = json_file.read()
audio_model = tf.keras.models.model_from_json(loaded_model_json_2)
audio_model.load_weights("model_weights.h5")


# Load the tokenizer
with open('tokenizer1.json') as f:
    data = json.load(f)
    tokenizer = tf.keras.preprocessing.text.tokenizer_from_json(data)

# Prediction route
@app.route('/predict_text', methods=['GET', 'POST'])
def predict_text():
    if request.method == 'POST':
        data = request.get_json()
        print("c")
        if 'user_input' not in data:
            return jsonify({'error': 'Missing user_input parameter'}), 400

        user_input = data['user_input']

        # Tokenize and pad the input
        sequences_test_final = tokenizer.texts_to_sequences([user_input])
        nlp_test_final = pad_sequences(sequences_test_final, maxlen=50)

        # Make prediction
        y_pred = text_model.predict([nlp_test_final])
        print(y_pred)
        # Assuming your model output is binary, you can adjust this part accordingly
        prediction = "Spam" if y_pred[0][0] > 0.5 else "Not Spam"

        return jsonify({'prediction': prediction})

    # Return a default response for GET requests
    return jsonify({'error': 'Invalid request method'}), 400

# Define a route for making predictions
@app.route('/predict_audio', methods=['GET', 'POST'])
def predict_audio():
   
     file = request.files['file']
    
     if file:
        sound_signal, sample_rate = librosa.load(file, sr=None, res_type="kaiser_fast")  # Add sr=None
        mfcc_features = librosa.feature.mfcc(y=sound_signal, sr=sample_rate, n_mfcc=40)
        mfccs_features_scaled = np.mean(mfcc_features.T, axis=0)
        mfccs_features_scaled = mfccs_features_scaled.reshape(1, -1)
        result_array = audio_model.predict(mfccs_features_scaled)
        print(result_array)
        result_classes = ["REAL", "FAKE"]
        result = np.argmax(result_array[0])
        print("Result:", result_classes[result])
        return jsonify({'predictions':  result_classes[result]})
     else:
        return "Invalid file"


if __name__ == '__main__':
    app.run(debug=True)
