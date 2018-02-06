# サイト解析
Webサイトの解析を行う

## インターフェース
### 入力
#### Pub/Subメッセージの構成
| Message   | 説明             |
| ---       | ---              |
| Data      | JSON形式の文字列 |
| Attribute | -                |

#### Data
| キー         | 値                              | データ型 |
| ---          | ---                             | ---     |
| url          | クローリングで取得したサイトのurl    | 文字列   |
| headers    | 対象サイト取得時のResponseヘッダ    | リスト   |
| header[].type    | ヘッダ種別    | 文字列   |
| header[].value    | ヘッダ値    | 文字列   |
| body         | 対象サイトのBody要素（HTML）       | 文字列   |

##### サンプルJSON
```json
{
  "url": "http://xxx.com",
  "headers": [
      {
          "Content-Length":"1024"
      },
      {
          "Content-Type": "text/html; charset=utf_8"
      }
  ],
  "body":"<html><head></head><body>hoge fuga piyo hogera</body></html>"
}
```

#### メッセージPublishサンプル
##### gcloud SDK
```console
$ gcloud pubsub topics publish {TOPIC_NAME} --message '{"url": "http://xxx.com","headers": [{"Content-Length":"1024"},{"Content-Type": "text/html; charset=utf_8"}],"body":"<html><head></head><body>hoge fuga piyo hogera</body></html>"}'
```

##### REST API
###### HTTP request
POST https://pubsub.googleapis.com/v1/{topic}:publish

###### Request body
```json
{
  "messages": [
    {
      "data": "ew0KICAidXJsIjogImh0dHA6Ly94eHguY29tIiwNCiAgImhlYWRlcnMiOiBbDQogICAgICB7DQogICAgICAgICAgIkNvbnRlbnQtTGVuZ3RoIjoiMTAyNCINCiAgICAgIH0sDQogICAgICB7DQogICAgICAgICAgIkNvbnRlbnQtVHlwZSI6ICJ0ZXh0L2h0bWw7IGNoYXJzZXQ9dXRmXzgiDQogICAgICB9ICAgICAgDQogIF0sDQogICJib2R5IjoiPGh0bWw+PGhlYWQ+PC9oZWFkPjxib2R5PmhvZ2UgZnVnYSBwaXlvIGhvZ2VyYTwvYm9keT48L2h0bWw+Ig0KfQ=="
    }
  ]
}
```

\# `data`はbase64エンコードする


### 出力
[レガシーサイト判定.インターフェース#入力](https://github.com/trash-dev-camp/lgbot-judge/blob/master/README.md) 参照
