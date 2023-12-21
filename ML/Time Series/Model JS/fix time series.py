import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from statsmodels.tsa.arima.model import ARIMA

# Membuat dataset dummy
np.random.seed(42)
date_rng = pd.date_range(start='2023-01-01', end='2024-01-01', freq='D')
data = np.random.randint(1, 100, size=(len(date_rng)))

# Membuat DataFrame
df = pd.DataFrame(data, columns=['Investment'], index=date_rng)

# Visualisasi dataset dummy
plt.figure(figsize=(10, 6))
plt.plot(df['Investment'], label='Investment')
plt.title('Dummy Time Series Dataset - Investment Over Time')
plt.xlabel('Date')
plt.ylabel('Investment Amount')
plt.legend()
plt.show()

# Memisahkan data menjadi set pelatihan dan set pengujian
train_size = int(len(df) * 0.8)
train, test = df[:train_size], df[train_size:]

# Membuat dan melatih model ARIMA
order = (5, 1, 0)  # Order model ARIMA (p, d, q)
model = ARIMA(train['Investment'], order=order)
fit_model = model.fit()

# Simpan model ARIMA ke dalam file .h5
fit_model.save("arima_model.h5")

# Prediksi menggunakan model
predictions = fit_model.predict(start=len(train), end=len(train) + len(test) - 1, typ='levels')

# Visualisasi hasil prediksi
plt.figure(figsize=(10, 6))
plt.plot(train['Investment'], label='Training Data')
plt.plot(test['Investment'], label='Test Data')
plt.plot(predictions, label='Predictions', linestyle='dashed')
plt.title('ARIMA Model - Investment Prediction')
plt.xlabel('Date')
plt.ylabel('Investment Amount')
plt.legend()
plt.show()


import json

# Menggabungkan data test dan prediksi
results = pd.concat([test, predictions], axis=1)
results.columns = ['Test Data', 'Predictions']

# Menyimpan hasil prediksi ke dalam file JSON
json_data = results.to_json(orient='split')

# Menyimpan data JSON ke dalam file
with open('arima_predictions.json', 'w') as json_file:
    json_file.write(json_data)
