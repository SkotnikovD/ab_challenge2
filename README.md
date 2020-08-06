# Решение задачи №2 с чемпионата по прикладному приграммированию [AlfaBattle](https://alfabattle.ru/)
## Условие

В топике Kafka RAW_PAYMENTS находятся данные по платежам пользователей.
Брокер развернут на виртуальной машине в docker контейнере и доступен по IP:29092 снаружи.  
**Следует учесть, что данные из Kafka могут приходить битыми.**

## Задачи
### 1. Разбиение данных по категориям

Необходимо выполнить разбиение данных платежей по категориям (поле categoryId).  
По каждой категории платежа пользователя необходимо подсчитать минимальную сумму, максимальную сумму и общую сумму в категории.

Кроме того требуется отобразить общую сумму платежей пользователя по всем категориям.

Запрос GET IP:8081/admin/health  
Ответ: {"status":"UP"}  

Зпрос GET http://IP:8081/analytic</h4>  
Возвращает аналитику платежей по всем пользователям  
Ответ: 
```json
[
  {
    "userId": "User_3",
    "totalSum": 71.981,
    "analyticInfo": {
      "3": {
        "min": 35.99,
        "max": 35.991,
        "sum": 71.981
      }
    }
  },
  {
    "userId": "User_2",
    "totalSum": 1051.68,
    "analyticInfo": {
      "2": {
        "min": 350.56,
        "max": 350.56,
        "sum": 1051.68
      }
    }
  },
  {
    "userId": "User_1",
    "totalSum": 2891.55,
    "analyticInfo": {
      "1": {
        "min": 10,
        "max": 890,
        "sum": 2520
      },
      "2": {
        "min": 350.56,
        "max": 350.56,
        "sum": 350.56
      },
      "3": {
        "min": 5.99,
        "max": 5.99,
        "sum": 5.99
      },
      "4": {
        "min": 15,
        "max": 15,
        "sum": 15
      }
    }
  }
]
````

Ключи структуры analyticInfo являются Id категорий платежей.  

Зарпос GET IP:8081/analytic/{userId}  
Ответ: (200/404 + тело {«status»:”user not found"} (если пользователь не найден))  
Получение аналитики по конкретному пользователю  

Пример:  
GET http://IP:8081/analytic/User_1  
Ответ:
```json
{
  "userId": "User_1",
  "totalSum": 2891.55,
  "analyticInfo": {
    "1": {
      "min": 10,
      "max": 890,
      "sum": 2520
    },
    "2": {
      "min": 350.56,
      "max": 350.56,
      "sum": 350.56
    },
    "3": {
      "min": 5.99,
      "max": 5.99,
      "sum": 5.99
    },
    "4": {
      "min": 15,
      "max": 15,
      "sum": 15
    }
  }
}
````

### 2. Статистика

Требуется уметь отображать статистику категорий по каждому пользователю.   
Статистика:
* самая частая категория трат
* самая редкая категория трат
* категория с наибольшей суммой
* категория с наименьшей суммой

GET IP:8081/analytic/{userId}/stats  

— 200  
— 404 + тело {«status»:”user not found"} (если пользователь не найден)   
Получение статистики категорий по пользователю. 

Пример запроса:  
GET http://IP:8081/analytic/User_1/stats  
Пример ответа:  
```json
{
  "oftenCategoryId": 1,
  "rareCategoryId": 2,
  "maxAmountCategoryId": 1,
  "minAmountCategoryId": 3
}
````

### 3. Шаблоны платежей

Требуется реализовать логику выделения шаблонов платежей из потока данных.  

Платеж считается шаблонным по следующим критериям:
* У платежей совпадают величина, категория, от кого и кому платеж был проведен (recipientId и userId соответственно)
* Такие платежи повторяются три и более раза

Реализовать интерфейс, по которому можно будет получать шаблоны платежей пользователя.  
GET IP:8081/analytic/{userId}/templates  
— 200  
— 404 + тело {«status»:”user not found"} (если пользователь не найден)  
Получение информации по платежам, которые были выделены как шаблонные для данного пользователя.  

Пример запроса:
GET http://IP:8081/analytic/User_1/templates  
Пример ответа:
```json
[
  {
    "recipientId": "User_2",
    "categoryId": 1,
    "amount": 10
  }
]
````