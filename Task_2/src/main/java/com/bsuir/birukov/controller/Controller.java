/*package main.java.com.bsuir.birukov.controller;

@RestController
@RequestMapping("/lcl")
@Slf4j
public class Controller {

    @Autowired
    private Cache cache;
    @Autowired
    private RequestCounter requestCounter;
    @Autowired
    private NumberRepository numberRepository;

    @GetMapping
    public synchronized ResponseEntity<Number> readNumberFromRequest(@RequestParam String notConvertedInteger) {

        requestCounter.incrementCounter();                                        //Инкрементация счётчика запросов
        log.info("Thread - " + requestCounter.getName() + "   //GET");
        log.info("Received number[" + requestCounter.getCounter() + "] - " + notConvertedInteger);

        if (cache.searchKey(notConvertedInteger)) {                             //Поиск значения notConvertedInteger в кэше
            log.info("This key[" + notConvertedInteger + "] are exist");        //В случае нахождения значения в кэше, возврат данного
            return ResponseEntity.ok(cache.getValue(notConvertedInteger));      //значения из кэша
        }

        Integer convertedInteger = new ArrayList<String>(Collections.singleton(notConvertedInteger)).stream()
                .map(FunctionUtil::validateNumber)                      //Валидация входных параметров
                .collect(Collectors.toList()).get(0);

        Number objectOfNumber = new Number(convertedInteger);
        log.info("objectOfNumber - " + objectOfNumber.getId() + ", " + objectOfNumber.getNumber());
        if (numberRepository.existsById(convertedInteger)) {
            log.info("This data are exist in DataBase");
//            return ResponseEntity.ok(objectOfNumber);
        } else {
            log.info("Send new data in DataBase");
            numberRepository.save(objectOfNumber);                          //Добавление значений в БД
            cache.addCacheValue(notConvertedInteger, objectOfNumber);       //Добавление значений в кэш
            log.info("Data in cache: " + cache.toString());
            log.info("CacheStat:" + StatComputationClass.statComputation(cache));    //Вывод статистики имеющихся данных
        }

        return ResponseEntity.ok(objectOfNumber);
    }

    @PostMapping
    public synchronized ResponseEntity<Number> newNumber(@RequestBody Number num) {
        requestCounter.incrementCounter();                              //Инкрементация счётчика запросов
        log.info("Thread - " + requestCounter.getName() + "   //POST");
        log.info("Received Number object[" + requestCounter.getCounter() + "] - " + num.toString());
        if (num.getNumber() == null) {
            throw new IllegalArgumentException("Error 400 - Bad Request");
        }
        Number objectOfNumber = new Number(new ArrayList<String>(Collections.singleton(num.getNumber().toString())).stream()
                .map(FunctionUtil::validateNumber)                      //Валидация входных параметров
                .collect(Collectors.toList()).get(0));

        objectOfNumber.setId(num.getId());
        log.info("object Of Number - " + objectOfNumber.getId().toString() + ", " + objectOfNumber.getNumber().toString());
        try {     //Проверяем наличие значения в кэше
            if (!cache.searchKey(objectOfNumber.getId().toString())) {                    //Значение нет в кэше
                if (numberRepository.existsById(objectOfNumber.getId()) &&
                        numberRepository.getById(objectOfNumber.getId()).getNumber().equals(objectOfNumber.getNumber())) {                 //Проверяем наличие в БД
                    log.info("This data are exist in DataBase");
                } else {
                    log.info("Send new data in DataBase");
                    numberRepository.save(objectOfNumber);                    //Запись данных в БД
                    log.info("The value " + objectOfNumber.getNumber().toString() + " aren't exist and will be created");
                    cache.addCacheValue(objectOfNumber.getNumber().toString(), objectOfNumber);   //Добавляем значение в кэш
                    log.info("CacheStat:" + StatComputationClass.statComputation(cache));         //Вывод статистики имеющихся данных
                }
                return ResponseEntity.ok(objectOfNumber);
            } else {
                if (cache.getValue(objectOfNumber.getId().toString()).getNumber()        //Замена значения в кэше и БД
                        .equals(objectOfNumber.getNumber())) {
                    log.info("This data are exist in cache (rewrite)");
                    cache.deleteByID(objectOfNumber.getId().toString());                //Дописать
                    cache.addCacheValue(objectOfNumber.getId().toString(), objectOfNumber);
                    numberRepository.save(objectOfNumber);
                    return ResponseEntity.ok(objectOfNumber);
                }
                throw new Exception();
            }
        } catch (Exception e) {                                                  //Значения есть в кэше
            log.info("The value " + objectOfNumber.getNumber().toString() + " are exist in cache");
            return ResponseEntity.ok(cache.getValue(objectOfNumber.getNumber().toString()));
        }
    }

}*/
