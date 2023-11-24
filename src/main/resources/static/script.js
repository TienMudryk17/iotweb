// tạo ngẫu nhiên + đổi màu ngẫu nhiên
function getColorBasedOnTemperatureValue(value, min, max) {
    const range = max - min;
    const percent = value / range;

    // Tùy thuộc vào percent, bạn có thể xác định màu nền tương ứng.
    if (percent < 0.3) {
        return "#009298"; // <18 xanh dương
    } else if (percent < 0.416) {
        return "#99D1D3"; // <25 xanh dương nhạt
    } else if (percent < 0.5) {
        return "#5BBD2B"; // <30 xanh lá
    } else if (percent < 0.583) {
        return "#F9F400"; // < 35 vàng
    } else if (percent < 0.65) {
        return "#EB7153"; // <39 cam
    } else if (percent < 0.75) {
        return "#E54646"; // <45 đỏ hồng
    } else if (percent < 0.833) {
        return "#8B0016"; // < 50 đỏ đậm
    } else {
        return "#A2007C"; // 51-60 tím
    }
}

function getColorBasedOnHumidityValue(value, min, max) {
    const range = max - min;
    const percent = (value - min) / range;

    // Tùy thuộc vào percent, bạn có thể xác định màu nền tương ứng.
    if (percent < 0.1) {
        return "red";
    } else if (percent < 0.5) {
        return "yellow";
    } else {
        return "green";
    }
}

function getColorBasedOnLightValue(value, min, max) {
    const range = max - min;
    const percent = (value - min) / range;

    // Tùy thuộc vào percent, bạn có thể xác định màu nền tương ứng.
    if (percent < 0.3) {
        return "#009298";
    } else if (percent < 0.416) {
        return "#00CDCD";
    } else if (percent < 0.5) {
        return "#5BBD2B";
    } else if (percent < 0.583) {
        return "#F9F400";
    } else if (percent < 0.65) {
        return "#EB7153";
    } else if (percent < 0.75) {
        return "#EE0000";
    } else if (percent < 0.833) {
        return "#DC143C";
    } else {
        return "#A2007C";
    }
}

function updateEnvironment() {
    // Tạo giá trị ngẫu nhiên cho nhiệt độ, độ ẩm và ánh sáng.
    const minTemperature = -10;
    const maxTemperature = 50;
    const randomTemperature = Math.floor(Math.random() * (maxTemperature - minTemperature + 1)) + minTemperature;

    const minHumidity = 40;
    const maxHumidity = 100;
    const randomHumidity = Math.floor(Math.random() * (maxHumidity - minHumidity + 1)) + minHumidity;

    const minLight = 100;
    const maxLight = 10000;
    const randomLight = Math.floor(Math.random() * (maxLight - minLight + 1)) + minLight;

    // Cập nhật giá trị vào thẻ HTML.
    const temperatureValueElement = document.getElementById("temperatureValue");
    const humidityValueElement = document.getElementById("humidityValue");
    const lightValueElement = document.getElementById("lightValue");

    temperatureValueElement.textContent = `${randomTemperature} °C`;
    humidityValueElement.textContent = `${randomHumidity} %`;
    lightValueElement.textContent = `${randomLight} lux`;

    // Xác định màu nền dựa trên giá trị và áp dụng màu nền cho từng cột.
    const temperatureColumnElement = document.getElementById("temperatureColumn");
    const humidityColumnElement = document.getElementById("humidityColumn");
    const lightColumnElement = document.getElementById("lightColumn");

    temperatureColumnElement.style.backgroundColor = getColorBasedOnTemperatureValue(randomTemperature, minTemperature, maxTemperature);
    humidityColumnElement.style.backgroundColor = getColorBasedOnHumidityValue(randomHumidity, minHumidity, maxHumidity);
    lightColumnElement.style.backgroundColor = getColorBasedOnLightValue(randomLight, minLight, maxLight);
}

// Gọi hàm cập nhật ban đầu và sau mỗi 1 giây.
updateEnvironment();
setInterval(updateEnvironment, 2000); // Cập nhật sau mỗi 1 giây.

// Đồ thị
const canvas = document.getElementById('sensorChart');
const ctx = canvas.getContext('2d');

// Tạo biểu đồ đa dạng để hiển thị nhiệt độ, độ ẩm và ánh sáng
const sensorChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
            label: 'Temperature (°C)',
            data: [],
            borderColor: '#A52A2A',
            borderWidth: 2,
            fill: true,
            backgroundColor: 'rgba(255, 99, 132, 0.2)'
        }, {
            label: 'Humidity (%)',
            data: [],
            borderColor: 'green',
            borderWidth: 2,
            fill: true,
            backgroundColor: 'rgba(54, 162, 235, 0.2)'
        }, {
            label: 'Light (Lux)',
            data: [],
            borderColor: 'orange',
            borderWidth: 2,
            fill: true,
            backgroundColor: 'rgba(255, 206, 86, 0.2)'
        }]
    },
    options: {
        responsive: false,
        scales: {
            x: {
                type: 'category',
                position: 'bottom'
            },
            y: {
                beginAtZero: true,
                suggestedMax: 1000
            }
        }
    }
});

function updateSensorData() {
    // Tạo các giá trị ngẫu nhiên cho nhiệt độ, độ ẩm và ánh sáng
    const minTemperature = 0;
    const maxTemperature = 40;
    const randomTemperature = Math.floor(Math.random() * (maxTemperature - minTemperature + 1)) + minTemperature;

    const minHumidity = 0;
    const maxHumidity = 100;
    const randomHumidity = Math.floor(Math.random() * (maxHumidity - minHumidity + 1)) + minHumidity;

    const minLightIntensity = 0;
    const maxLightIntensity = 1000;
    const randomLightIntensity = Math.floor(Math.random() * (maxLightIntensity - minLightIntensity + 1)) + minLightIntensity;

    // Lấy thời gian hiện tại để làm nhãn trên trục x
    const currentTime = new Date().toLocaleTimeString();

    // Thêm giá trị mới vào các mảng dữ liệu
    var dataLimit = 30;
    if (sensorChart.data.labels.length >= dataLimit) {
        sensorChart.data.labels.shift(); // Xóa nhãn cũ nhất
        sensorChart.data.datasets[0].data.shift(); // Xóa dữ liệu cũ nhất cho nhiệt độ
        sensorChart.data.datasets[1].data.shift(); // Xóa dữ liệu cũ nhất cho độ ẩm
        sensorChart.data.datasets[2].data.shift(); // Xóa dữ liệu cũ nhất cho ánh sáng
    }
    sensorChart.data.labels.push(currentTime);
    sensorChart.data.datasets[0].data.push(randomTemperature);
    sensorChart.data.datasets[1].data.push(randomHumidity);
    sensorChart.data.datasets[2].data.push(randomLightIntensity);

    // Cập nhật biểu đồ
    sensorChart.update();
}

// Cập nhật dữ liệu cả 3 loại sensor ban đầu
updateSensorData();

// Cập nhật dữ liệu sau mỗi 1 giây
setInterval(updateSensorData, 2000);
//Led 1 & 2 ON OFF
var led1State = false; // Trạng thái của LED 1 (ban đầu là tắt)
var led2State = false; // Trạng thái của LED 2 (ban đầu là tắt)

function toggleLED(buttonId) {
    var button = document.getElementById(buttonId);

    if (button.classList.contains('btn-on')) {
        button.style.backgroundColor = '#4CAF50'; // Bật LED (đặt màu xanh)
        button.nextElementSibling.style.backgroundColor = ''; // Tắt nút OFF
        if (buttonId === 'btnOn1') {
            led1State = true; // LED 1 bật
        } else if (buttonId === 'btnOn2') {
            led2State = true; // LED 2 bật
        }
    } else if (button.classList.contains('btn-off')) {
        // Nếu nút là OFF
        button.style.backgroundColor = '#4CAF50'; // Tắt LED (đặt màu đỏ)
        button.previousElementSibling.style.backgroundColor = ''; // Tắt nút ON
        if (buttonId === 'btnOff1') {
            led1State = false; // LED 1 tắt
        } else if (buttonId === 'btnOff2') {
            led2State = false; // LED 2 tắt
        }
    }
}

// Làm sáng một trong các nút ON/OFF khi trạng thái LED thay đổi
function updateButtonState() {
    if (led1State) {
        document.getElementById('btnOn1').style.backgroundColor = '#4CAF50';
        document.getElementById('btnOff1').style.backgroundColor = '';
    } else {
        document.getElementById('btnOn1').style.backgroundColor = '';
        document.getElementById('btnOff1').style.backgroundColor = '';
    }

    if (led2State) {
        document.getElementById('btnOn2').style.backgroundColor = '#4CAF50';
        document.getElementById('btnOff2').style.backgroundColor = '';
    } else {
        document.getElementById('btnOn2').style.backgroundColor = '';
        document.getElementById('btnOff2').style.backgroundColor = '';
    }
}

// Cập nhật trạng thái nút khi trang web được tải
updateButtonState();