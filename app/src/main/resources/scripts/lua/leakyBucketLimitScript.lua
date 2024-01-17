-- 漏桶限流算法脚本

local key = KEYS[1]
-- 漏桶容量
local threshold = tonumber(ARGV[1])
-- 消费请求的速率：个/秒
local rate = tonumber(ARGV[2])
-- 当前请求时间
local currentTime = tonumber(ARGV[3])
local key_lifetime = math.ceil((threshold / rate) * 1000)

-- 当前漏桶剩余未消耗的请求数
local leftCount = tonumber(redis.call('hget', key, 'leftCount')) or 0
-- 上次处理请求的时间
local lastRequestTime = tonumber(redis.call('hget', key, 'lastRequestTime')) or currentTime

-- 计算从上一个请求的时间到当前，应当消耗了多少请求
local consumeCount = (currentTime - lastRequestTime) / 1000  * rate

-- 非第一次请求，要计算漏桶中剩余的请求量
if consumeCount > 0
then
    leftCount = math.max(0, leftCount - consumeCount)
    lastRequestTime = currentTime
end

-- 请求是否成功
local acquire = 0
if leftCount + 1 <= threshold
then
    acquire = 1
else
    return acquire
end

-- 更新
redis.call('hmset', key, 'leftCount', leftCount + 1, 'lastRequestTime', lastRequestTime)
redis.call('PEXPIRE', key, key_lifetime)
return acquire


