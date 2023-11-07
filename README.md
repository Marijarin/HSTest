# HSTest
* Реализовано частичное сворачивание аппбара
* Реализована подгрузка из БД (Room), если нет интернета. Если в БД тоже ничего нет, то загружается пустой список
* Если нет интернета и поэтому загрузить с сервера не удалось, ошибка отражается в логах
* В зависимости от выбранной категории из списка под аппбаром меняется список отображаемых блюд
* Если ни один Chip не выбран, то показываются все блюда
* Clean Architecture (data layer, domain layer, presentation layer) с разными моделями для загрузки данных, сохранении в БД и отображении во фрагменте. UseCase не добавляла, т.к. согласно документации опционально, если нет сложного извлечения данных.
* Navigation настроена (можно перейти во все три фрагмента, два фрагмента с заглушками)
* Для потоков данных используется Kotlin Flow
* app-debug.apk добавлена сюда, чтобы было все вместе
