-- 固定窗口限流算法
local count = redis.call("INCR", KEYS[1])
if tonumber(count) == 1
then
    redis.call("PEXPIRE", KEYS[1], ARGV[1])
end
return count