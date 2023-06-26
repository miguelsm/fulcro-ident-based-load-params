From `src/dev/user.clj`:

``` clojure
(comment
  (def server-property-query `[{(:people {:foo "bar"}) [:person/name]}])
  (eql/query->ast server-property-query) ;; => has `:params {:foo "bar"}`
  (app.parser/api-parser server-property-query) ;; => logs `params: {:foo "bar"}`

  (def ident-based-query `[{([:person/id 1] {:foo "bar"}) [:person/name]}])
  (eql/query->ast ident-based-query) ;; => has `:params {:foo "bar"}`
  (app.parser/api-parser ident-based-query) ;; logs `params: nil`
  ,)
```

This is due to how Pathom works, it can be solved in Fulcro by using the `:update-query` option from `load!`:
https://github.com/wilkerlucio/pathom/issues/93
