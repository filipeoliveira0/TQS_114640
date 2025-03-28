# 7_1 d)     
     
     
     
     execution: local
        script: test.js
        output: -

     scenarios: (100.00%) 1 scenario, 1 max VUs, 10m30s max duration (incl. graceful stop):
              * default: 1 iterations for each of 1 VUs (maxDuration: 10m0s, gracefulStop: 30s)

INFO[0000] An Orange Quattro Stagioni (5 ingredients)    source=console


  █ TOTAL RESULTS 

    HTTP
    http_req_duration.......................................................: avg=68.7ms  min=68.7ms  med=68.7ms  max=68.7ms  p(90)=68.7ms  p(95)=68.7ms 
      { expected_response:true }............................................: avg=68.7ms  min=68.7ms  med=68.7ms  max=68.7ms  p(90)=68.7ms  p(95)=68.7ms 
    http_req_failed.........................................................: 0.00% 0 out of 1
    http_reqs...............................................................: 1     14.21925/s

    EXECUTION
    iteration_duration......................................................: avg=70.04ms min=70.04ms med=70.04ms max=70.04ms p(90)=70.04ms p(95)=70.04ms
    iterations..............................................................: 1     14.21925/s

    NETWORK
    data_received...........................................................: 690 B 9.8 kB/s
    data_sent...............................................................: 365 B 5.2 kB/s




running (00m00.1s), 0/1 VUs, 1 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  00m00.1s/10m0s  1/1 iters, 1 per VU



# 7.2 a)

█ TOTAL RESULTS 

    HTTP
    http_req_duration.......................................................: avg=233.08ms min=13.63ms med=206.84ms max=1.11s p(90)=428.43ms p(95)=503.64ms
      { expected_response:true }............................................: avg=233.08ms min=13.63ms med=206.84ms max=1.11s p(90)=428.43ms p(95)=503.64ms
    http_req_failed.........................................................: 0.00%  0 out of 1309
    http_reqs...............................................................: 1309   65.219279/s

    EXECUTION
    iteration_duration......................................................: avg=233.69ms min=13.9ms  med=207.79ms max=1.11s p(90)=429.04ms p(95)=504.32ms
    iterations..............................................................: 1309   65.219279/s
    vus.....................................................................: 1      min=1         max=20
    vus_max.................................................................: 20     min=20        max=20

    NETWORK
    data_received...........................................................: 942 kB 47 kB/s
    data_sent...............................................................: 478 kB 24 kB/s




running (20.1s), 00/20 VUs, 1309 complete and 0 interrupted iterations
default ✓ [======================================] 00/20 VUs  20s


# 7.2 d)


  █ THRESHOLDS 

    checks
    ✗ 'rate>0.98' rate=97.94%

    http_req_duration
    ✗ 'p(95)<1100' p(95)=1.46s

    http_req_failed
    ✗ 'rate<0.01' rate=4.11%


  █ TOTAL RESULTS 

    checks_total.......................: 3308   36.731146/s
    checks_succeeded...................: 97.94% 3240 out of 3308
    checks_failed......................: 2.05%  68 out of 3308

    ✗ is status 200
      ↳  95% — ✓ 1586 / ✗ 68
    ✓ body size is lower than 1KB

    HTTP
    http_req_duration.......................................................: avg=737.05ms min=34.77ms med=691.57ms max=1.98s p(90)=1.28s p(95)=1.46s
      { expected_response:true }............................................: avg=701.07ms min=34.77ms med=678.93ms max=1.94s p(90)=1.17s p(95)=1.31s
    http_req_failed.........................................................: 4.11%  68 out of 1654
    http_reqs...............................................................: 1654   18.365573/s

    EXECUTION
    iteration_duration......................................................: avg=737.58ms min=35.06ms med=692.05ms max=1.98s p(90)=1.28s p(95)=1.46s
    iterations..............................................................: 1654   18.365573/s
    vus.....................................................................: 1      min=1          max=20
    vus_max.................................................................: 20     min=20         max=20

    NETWORK
    data_received...........................................................: 1.2 MB 13 kB/s
    data_sent...............................................................: 604 kB 6.7 kB/s




running (1m30.1s), 00/20 VUs, 1654 complete and 0 interrupted iterations
default ✓ [======================================] 00/20 VUs  1m30s
ERRO[0090] thresholds on metrics 'checks, http_req_duration, http_req_failed' have been crossed 



