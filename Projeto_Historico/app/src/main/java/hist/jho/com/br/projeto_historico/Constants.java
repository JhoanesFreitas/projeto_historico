/*
 * Copyright (c) 2016. jhoanesfreitas
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *            http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package hist.jho.com.br.projeto_historico;

/**
 * Created by jhoanesfreitas on 19/04/16.
 */
public class Constants{

  //Constant for splash activity
  public final static int SECONDS_DELAYED = 500;

  //Constants for gps
  public final static long MIN_DISTANCE_CHANCE_FOR_UPDATES = 10;
  public final static long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
  public final static long TIME_UPDATES_REFRESH = 2500;
  public final static String NOT_FOUND = "Nenhum provedor de rede está habilitado!";
  public final static String TITLE = "GPS is settings";
  public final static String MSG = "GPS não está habilitado! Habilite-o em Configurações!";
  public final static String CANCEL = "Cancel";
  public final static String TEXT_ACTIVITY = "text_activity";
  public final static String TEXT_ACTIVITY_TITLE = "text_title";
  public static boolean POP = false;


}
