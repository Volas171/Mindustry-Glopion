/*******************************************************************************
 * Copyright 2021 Itzbenz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

/* o7 Inc 2021 Copyright

  Licensed under the o7 Inc License, Version 1.0.1, ("the License");
  You may use this file but only with the License. You may obtain a
  copy of the License at
  
  https://github.com/o7-Fire/Mindustry-Ozone/Licenses
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the license for the specific language governing permissions and
  limitations under the License.
*/

package org.o7.Fire.Glopion.Event;

public class EventExtended {
    public enum Connect {
        Disconnected, Connected
    }
    
    public enum Game {
        Start, Stop
    }
    
    public static class Connecting {
        public String ip;
        public int port;
        
        public Connecting(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }
    }
    
    public static class Shutdown {
    
    }
    
    public static class Log {
        public String raw, result;
        public arc.util.Log.LogLevel level;
        
        public Log(String raw, String result, arc.util.Log.LogLevel level) {
            this.raw = raw;
            this.result = result;
            this.level = level;
        }
    }
}