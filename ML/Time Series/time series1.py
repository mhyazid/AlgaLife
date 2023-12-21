import pandas as pd
import matplotlib.pyplot as plt
from statsmodels.tsa.statespace.sarimax import SARIMAX
from sklearn.model_selection import TimeSeriesSplit
from sklearn.metrics import mean_absolute_error

# Contoh pembuatan DataFrame dengan kolom 'Tanggal' dan 'Keuntungan'
data = {'Tanggal': ['2023-01-01', '2023-01-02', '2023-01-03'],
        'Keuntungan': [1000, 1200, 800]}
df = pd.DataFrame(data)

# Ubah tipe data 'Tanggal' menjadi datetime
df['Tanggal'] = pd.to_datetime(df['Tanggal'])
df.set_index('Tanggal', inplace=True)

# Contoh: Plot data untuk melihat tren
plt.figure(figsize=(10, 6))
plt.plot(df['Keuntungan'])
plt.title('Time Series Data - Keuntungan Investasi')
plt.xlabel('Tanggal')
plt.ylabel('Keuntungan')
plt.show()

# Contoh: Menambahkan variabel lag
df['Keuntungan_Lag1'] = df['Keuntungan'].shift(1)


# Contoh: Membagi data untuk validasi silang
tscv = TimeSeriesSplit(n_splits=5)

# Contoh: Membuat model SARIMA
model = SARIMAX(df['Keuntungan'], order=(1, 1, 1), seasonal_order=(1, 1, 1, 12))

train_size = int(len(df) * 0.8)
train, test = df[:train_size], df[train_size:]

model_fit = model.fit(disp=False)
predictions = model_fit.get_forecast(steps=len(test))

# Contoh: Visualisasi hasil prediksi
plt.figure(figsize=(12, 6))
plt.plot(train.index, train['Keuntungan'], label='Train')
plt.plot(test.index, test['Keuntungan'], label='Test')
plt.plot(test.index, predictions.predicted_mean, label='Prediksi', color='red')
plt.title('Prediksi Keuntungan Investasi dengan SARIMA')
plt.xlabel('Tanggal')
plt.ylabel('Keuntungan')
plt.legend()
plt.show()

# Memperoleh nilai prediksi
predicted_values = predictions.predicted_mean

# Menampilkan nilai prediksi
print("Nilai Prediksi:")
print(predicted_values)

# Evaluasi performa model (contoh menggunakan MAE)
mae = mean_absolute_error(test['Keuntungan'], predicted_values)
print(f"Mean Absolute Error (MAE): {mae}")

