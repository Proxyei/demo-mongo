# 功能
1. 自定义配置多数据源  
1.1 使用URI方式   
1.2 使用MongoClientSettings方式   
   
2. 配置连接池参数

# 分组统计
```json
[{
    $match: {
        $and: [{
                projectId: '67'
            },
            {
                targetBranch: {
                    $in: [
                        'master66',
                        'master67'
                    ]
                }
            },
            {
                mergeTime: {
                    $in: [
                        '2022-10-17 07:37:39',
                        '2022-12-01 04:43:58'
                    ]
                }
            }
        ]
    }
}, {
    $project: {
        projectId: 1,
        targetBranch: 1,
        allTotal: {
            $add: [
                '$deleteLines',
                '$addLines'
            ]
        }
    }
}, {
    $group: {
        _id: [
            '$projectId'
        ],
        addTotal: {
            $sum: '$allTotal'
        }
    }
}]
```

```json
[{
 $match: {
  $and: [
   {
    projectId: '67'
   },
   {
    targetBranch: {
     $in: [
      'master66',
      'master67'
     ]
    }
   },
   {
    mergeTime: {
     $gte: '2022-10-17 07:37:39',
     $lte: '2022-12-01 04:43:58'
    }
   }
  ]
 }
}, {
 $project: {
  projectId: 1,
  targetBranch: 1,
  allTotal: {
   $add: [
    '$deleteLines',
    '$addLines'
   ]
  }
 }
}, {
 $group: {
  _id: [
   '$projectId'
  ],
  addTotal: {
   $sum: '$allTotal'
  }
 }
}]
```