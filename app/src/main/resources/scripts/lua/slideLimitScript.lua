-- 滑动窗口限流脚本

-- 获取基本信息
local key = KEYS[1]
local window = tonumber(ARGV[1])
local currentTime = tonumber(ARGV[2])
local threshold = tonumber(ARGV[3])
local randomKey = ARGV[4]
local startTime = currentTime - window

-- 先统计当前时间窗口内的请求次数
local requestCount = redis.call('zcount', key, startTime, currentTime)

-- 如果已超阈值，则直接返回
if requestCount and tonumber(requestCount) >= threshold
then
    return tonumber(requestCount)
end

-- 清除过期时间窗口数据，并将当前的访问记录添加到时间窗口内
redis.call('ZREMRANGEBYSCORE', key, 0, startTime)
redis.call('ZADD', key, currentTime, randomKey)
redis.call('PEXPIRE', key, window)

return tonumber(requestCount)