import tensorflow as tf

# Load model
keras_model = tf.keras.models.load_model("model.h5", compile=False)

# Khoi tao mot bo converter
converter = tf.lite.TFLiteConverter.from_keras_model(keras_model)
#converter.optimizations = [tf.lite.Optimize.DEFAULT]

# Thuc hien convert
tflite_model = converter.convert()

# Write in file
with open("model.tflite", 'wb') as f:
  f.write(tflite_model)