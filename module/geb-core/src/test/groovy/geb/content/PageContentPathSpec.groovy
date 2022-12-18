/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package geb.content

import geb.Module
import geb.Page
import geb.test.GebSpecWithCallbackServer

class PageContentPathSpec extends GebSpecWithCallbackServer {

    def "content path and root"() {
        given:
        html {
            div {
                p 'text'
                a 'text'
            }
        }
        to PageContentSpecPage

        expect:
        div.rootContainer instanceof PageContentSpecPage
        div.contentPath == ["div"]
        div.a.rootContainer instanceof PageContentSpecPage
        div.a.contentPath == ["div", "a"]
        div(1).a("foo").rootContainer instanceof PageContentSpecPage
        div(1).a("foo").contentPath == ["div", "a"]
        divWithArgs("foo", "bar").rootContainer instanceof PageContentSpecPage
        divWithArgs("foo", "bar").contentPath == ["divWithArgs"]
        div.p.rootContainer instanceof PageContentSpecPage
        div.p.contentPath == ["div", "p"]

        and:
        $('div').module(PageContentSpecModule).rootContainer instanceof PageContentSpecModule
        $('div').module(PageContentSpecModule).contentPath == []
        $('div').module(PageContentSpecModule).p.rootContainer instanceof PageContentSpecModule
        $('div').module(PageContentSpecModule).p.contentPath == ['p']
    }

}

class PageContentSpecPage extends Page {

    static content = {
        div { $('div').module(PageContentSpecModule) }
        divWithArgs { first, second -> $('div').module(PageContentSpecModule) }
    }

}

class PageContentSpecModule extends Module {

    static content = {
        p { $('p') }
        a { $('a').module(PageContentSpecInnerModule) }
    }

}

class PageContentSpecInnerModule extends Module {
}
