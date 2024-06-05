## 환경 설정
- [elasitcSearch 다운로드](https://www.elastic.co/kr/downloads/past-releases/elasticsearch-7-10-0)
- [filebeat 다운로드](https://www.elastic.co/kr/downloads/past-releases/filebeat-7-10-0)
- [kibana 다운로드](https://www.elastic.co/kr/downloads/past-releases/kibana-7-10-0)
- [logstash 다운로드](https://www.elastic.co/kr/downloads/past-releases/logstash-7-10-0)

### 1. LogStash 환경 설정 
다운로드 받은 logstach 압축을 풀면 /config 디렉토리에 `logstash-sample.conf`가 존재합니다. 

```bash
# Sample Logstash configuration for creating a simple
# Beats -> Logstash -> Elasticsearch pipeline.

input {
  beats {
    port => 5044
  }
}

output {
  elasticsearch {
    hosts => ["http://localhost:9200"]
    index => "%{[@metadata][beat]}-%{[@metadata][version]}-%{+YYYY.MM.dd}"
    #user => "elastic"
    #password => "changeme"
  }
}
```
여기서 설정 값을 변경해 elasticsearch와 연결합니다. 
연결이 되는지 확인해봅시다. 

**Elastic Search 실행** <br> 
`.\bin\elasticsearch`

동작 확인<br> 
`http://localhost:9200`

다음 JSON이 반환된다면 성공입니다. 
```bash
{
  "name" : "Gundorit-MacBook.local",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "zQxSCc3GS0Wk-lho8G0z0w",
  "version" : {
    "number" : "8.13.4",
    "build_flavor" : "default",
    "build_type" : "tar",
    "build_hash" : "da95df118650b55a500dcc181889ac35c6d8da7c",
    "build_date" : "2024-05-06T22:04:45.107454559Z",
    "build_snapshot" : false,
    "lucene_version" : "9.10.0",
    "minimum_wire_compatibility_version" : "7.17.0",
    "minimum_index_compatibility_version" : "7.0.0"
  },
  "tagline" : "You Know, for Search"
}
```

**LogStash와 연결** <br>
`.\bin\logstash -f .\config\logstash-sample.conf`

**에러 발생 (1)**
```bash
[ERROR] 2024-06-04 11:28:01.211 [main] Logstash - java.lang.IllegalStateException: Logstash stopped processing because of an error: (GemNotFound) You have requested:
  logstash-core >= 0

The bundle currently has logstash-core locked at 7.10.0.
Try running `bundle update logstash-core`

If you are updating multiple gems in your Gemfile at once,
try passing them all to `bundle update`
```


## Reference 
- https://russell-seo.tistory.com/29
- https://dnai-deny.tistory.com/72?category=1377211