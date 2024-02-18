-- 令牌桶限流算法脚本

local key = KEYS[1]
-- 令牌桶容量
local threshold = tonumber(ARGV[1])
-- 产生令牌的速度：rate个/秒
local rate = tonumber(ARGV[2])
-- 当前请求时间
local currentTime = tonumber(ARGV[3])

-- 当前剩余令牌数
local rest = tonumber(redis.call('hget', key, 'token'))
-- 上次更新令牌的时间
local refreshTime = tonumber(redis.call('hget', key, 'refreshTime'))

-- 如果第一次访问，默认将令牌桶填满
if rest == nil or refreshTime == nil
then
    rest = threshold
    refreshTime = currentTime
end

-- 计算从refreshTime到currentTime这段时间内产生的令牌数量
local generatedTokens = math.floor((currentTime - refreshTime) / 1000 * rate)
rest = math.min(threshold, rest + generatedTokens)

-- 获取令牌是否成功
local acquire = 0
if rest > 0
then
    acquire = 1
    rest = rest - 1
end

-- 过期时间设置为填满令牌桶所需时间的2倍
local ttl = math.floor(threshold / rate * 2)
redis.call('hset', key, 'token', rest)
redis.call('hset', key, 'refreshTime', currentTime)
redis.call('expire', key, ttl)
return acquire