//package br.com.insight.hourapp;
//
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.List;
//
//import br.com.insight.hourapp.web.entities.HourMarker;
//import br.com.insight.hourapp.web.entities.SummaryHour;
//import br.com.insight.hourapp.web.entities.WorkSchedule;
//import br.com.insight.hourapp.web.entities.enums.HourType;
//
///**
// * Testes dos horários informados...
// */
//public class HourCalcTests {
//
//	public static void main(String[] args) {
//		List<WorkSchedule> schedules = new ArrayList<>();
//		List<SummaryHour> newSummaries = new ArrayList<>();
//		HourMarker hm = null;
//
//		/**
//		 * Primeira Simulação
//		 */
//		schedules.add(new WorkSchedule("08:00", "12:00"));
//
//		// Primeiro Teste
//		hm = new HourMarker("08:00", "12:00");
//
//		newSummaries = calculateHours(schedules, hm);
//
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//		System.out.println(
//				"****************************************************************************************************");
//
//		// Segundo Teste
//		hm = new HourMarker("09:00", "12:00");
//
//		newSummaries = calculateHours(schedules, hm);
//
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//		System.out.println(
//				"****************************************************************************************************");
//
//		// Terceiro Teste
//		hm = new HourMarker("08:00", "11:00");
//
//		newSummaries = calculateHours(schedules, hm);
//
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//		System.out.println(
//				"****************************************************************************************************");
//
//		// Quarto Teste
//		hm = new HourMarker("08:00", "13:00");
//
//		newSummaries = calculateHours(schedules, hm);
//
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//		System.out.println(
//				"****************************************************************************************************");
//
//		// Quinto Teste
//		hm = new HourMarker("07:00", "13:00");
//
//		newSummaries = calculateHours(schedules, hm);
//
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//		System.out.println(
//				"****************************************************************************************************");
//
//		// Sexto Teste
//		hm = new HourMarker("06:00", "08:00");
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//
//		hm = new HourMarker("12:00", "13:00");
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//
//		System.out.println(
//				"****************************************************************************************************");
//
//		// Setimo Teste
//		hm = new HourMarker("06:00", "07:30"); // EXTRA -- 06:00 07:30 ATRASO -- 08:00 12:00
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//
//		hm = new HourMarker("08:15", "10:00"); // ATRASO -- 08:00 08:15 | 10:00 12:00
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//
//		hm = new HourMarker("10:10", "11:35"); // ATRASO -- 08:00 10:10 | 11:35 12:00
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//
//		hm = new HourMarker("11:50", "12:45"); // ATRASO -- 08:00 11:50 EXTRA 12:00 12:45
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//
//		System.out.println(
//				"****************************************************************************************************");
//
//		/**
//		 * Segunda Simulação
//		 */
//
//		schedules = new ArrayList<>();
//		schedules.add(new WorkSchedule("08:00", "12:00"));
//		schedules.add(new WorkSchedule("14:00", "18:00"));
//
//		// Primeiro Teste
//		hm = new HourMarker("08:00", "12:00"); // SEM ATRASO E EXTRA
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//
//		hm = new HourMarker("14:00", "18:00"); // SEM ATRASO E EXTRA
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//		System.out.println(
//				"****************************************************************************************************");
//
//		// Segundo Teste
//		hm = new HourMarker("07:00", "11:00");
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//
//		hm = new HourMarker("13:00", "17:00");
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//		System.out.println(
//				"****************************************************************************************************");
//
//		// Terceiro Teste
//		hm = new HourMarker("08:00", "12:00"); // SEM ATRASO E EXTRA
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//
//		hm = new HourMarker("14:00", "18:00"); // SEM ATRASO E EXTRA
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//
//		hm = new HourMarker("20:00", "23:00"); // SEM ATRASO E EXTRA
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//		System.out.println(
//				"****************************************************************************************************");
//		
//		// Quarto Teste
//		hm = new HourMarker("06:00", "23:00"); // EXTRA -- 06:00 08:00 | 12:00 14:00 | 18:00 23:00
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//		System.out.println(
//				"****************************************************************************************************");
//
//		/**
//		 * Terceira Simulação
//		 */
//
//		schedules = new ArrayList<>();
//		schedules.add(new WorkSchedule("07:00", "11:00"));
//		schedules.add(new WorkSchedule("13:00", "17:00"));
//		schedules.add(new WorkSchedule("19:00", "21:00"));
//
//		// Primeiro Teste
//		hm = new HourMarker("07:00", "11:00"); // SEM ATRASO E EXTRA
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//
//		hm = new HourMarker("13:00", "17:00"); // SEM ATRASO E EXTRA
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//		
//		hm = new HourMarker("19:00", "21:00"); // SEM ATRASO E EXTRA
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//		System.out.println(
//				"****************************************************************************************************");
//		
//		hm = new HourMarker("06:00", "23:00"); // EXTRA -- 06:00 07:00 | 11:00 13:00 | 17:00 19:00 | 21:00 23:00
//		newSummaries = calculateHours(schedules, hm);
//		newSummaries.forEach(s -> System.out.println(
//				"Periodo -> " + s.getTotalHours() + ", Tipo -> " + HourType.getDescriptionByCod(s.getHourType())));
//		System.out.println(
//				"****************************************************************************************************");
//	}
//
//	private static List<SummaryHour> calculateHours(List<WorkSchedule> schedules, HourMarker hm) {
//
//		List<SummaryHour> newSummaries = new ArrayList<>();
//		WorkSchedule schedule = null;
//
//		/**
//		 * Verifica se possuí mais de um horário, e se a jornada total do funcionário passou de 8 horas, pois se passar, 
//		 * ele pode ter passado por dois ou mais horários diferentes
//		 */
//		if (schedules.size() > 1 && calcularDiferencaHoras(hm.getEntryHour(), hm.getDepartureTime()) < 8) {
//			/**
//			 * Se existir mais de um horário
//			 */
//			schedule = findNearestSchedule(hm.getEntryHour(), schedules);
//
//			newSummaries.addAll(findOverOrLateHours(schedule, hm));
//
//		} else if(schedules.size() == 1){
//			/**
//			 * Quando existe apenas um horário
//			 */
//			schedule = schedules.get(0);
//			newSummaries.addAll(findOverOrLateHours(schedule, hm));
//		} else {
//			/**
//			 * Quando uma jornada ultrapassar 8 horas, ela pode estar em mais de um horário,
//			 * então é necessário percorrer todos os horários
//			 */
//			newSummaries.addAll(findOverOrLateHours(schedules, hm));
//		}
//
//		newSummaries.removeIf(s -> s.getTotalHours() == null);
//
//		return newSummaries;
//	}
//
//	private static List<SummaryHour> findOverOrLateHours(WorkSchedule schedule, HourMarker hm) {
//		List<SummaryHour> newSummaries = new ArrayList<>();
//		int markerEntryHour = Integer.parseInt(hm.getEntryHour().split(":")[0]);
//		int markerEntryMinute = Integer.parseInt(hm.getEntryHour().split(":")[1]);
//
//		int markerDepartureHour = Integer.parseInt(hm.getDepartureTime().split(":")[0]);
//		int markerDepartureMinute = Integer.parseInt(hm.getDepartureTime().split(":")[1]);
//
//		// Guarda a hora extra
//		String horaExtra = "";
//
//		// Guarda a hora de atraso
//		String horaAtraso = "";
//
//		// Início da jornada
//		int hour = 0;
//
//		// Guarda o último minuto processado
//		int lastMinute = 0;
//
//		WorkSchedule ultimoHorarioProcessado = null;
//
//		/**
//		 * Caso exista apenas 1 horário cadastrado verifica e calcula em cima dele
//		 */
//
//		boolean estaTrabalhando = false;
//		boolean entrouNoHorario = false;
//		boolean horaExtraAntecipadaDetectada = false;
//		boolean horaDeAtrasoAntecipadaDetectada = false;
//
//		boolean horaExtraAposDetectado = false;
//		boolean horaDeAtrasoAposDetectado = false;
//
//		int horarioDeEntrada = Integer.parseInt(schedule.getEntryHour().split(":")[0]);
//		int minutoDeEntrada = Integer.parseInt(schedule.getEntryHour().split(":")[1]);
//
//		int horarioDeSaida = Integer.parseInt(schedule.getDepartureTime().split(":")[0]);
//		int minutoDeSaida = Integer.parseInt(schedule.getDepartureTime().split(":")[1]);
//
//		lastMinute = minutoDeSaida;
//
//		/**
//		 * Jornada de Trabalho do funcionário
//		 * 
//		 * Se hour for == 0, então quer dizer que ele iniciou o cálculo agora, mas caso
//		 * já exista um valor, continua de onde parou Casos como uma jornada que passa
//		 * de um turno para o outro por exemplo....
//		 */
//		for (hour = hour == 0 ? markerEntryHour : hour; (hour - 1) < markerDepartureHour; hour++) {
//			ultimoHorarioProcessado = schedule;
//			if (hour == horarioDeSaida) {
//				/**
//				 * Isto marca a chegada ao final do expediente, então passa para o próximo
//				 * horário, caso exista Se não sai fora do loop, e caso ainda existam horas "em
//				 * haver" considera como hora extra
//				 */
//				break;
//			}
//
//			/**
//			 * Se passou o dia, o cálculo vai precisar ser um pouco diferente... Pois
//			 * precisaremos pegar o horário de início, de término e verificar quanto o
//			 * funcionário se atrasou
//			 * 
//			 * Se for maior, quer dizer que passou de um dia para o outro Ex: 22:00 05:00 |
//			 * 19:00 01:00 | 18:00 02:00
//			 */
//			if (horarioDeEntrada > horarioDeSaida) {
//				// Aqui verifica se eu cheguei antes ou depois do horário
//				GregorianCalendar calendar = new GregorianCalendar();
//
//				LocalDateTime dataHoraDeEntrada = LocalDateTime.of(calendar.get(GregorianCalendar.YEAR),
//						calendar.get(GregorianCalendar.MONTH) + 1, calendar.get(GregorianCalendar.DAY_OF_MONTH),
//						horarioDeEntrada, minutoDeEntrada);
//
//				LocalDateTime dataHoraMarcacao = LocalDateTime.of(calendar.get(GregorianCalendar.YEAR),
//						calendar.get(GregorianCalendar.MONTH) + 1, // Iguala
//						calendar.get(GregorianCalendar.DAY_OF_MONTH), markerEntryHour, markerEntryMinute);
//
//				/**
//				 * Como não foi fornecido um campo de data para fazer a comparação se passou de
//				 * um dia para o outro, sempre que for um valor muito menor que a o horário de
//				 * entrada, vou assumir que será um novo dia...
//				 */
//
//				long diferencaEmHoras = Math.abs(ChronoUnit.HOURS.between(dataHoraDeEntrada, dataHoraMarcacao));
//				if (diferencaEmHoras > 6) { // "Permite" até 6 horas de extra....
//					if (dataHoraMarcacao.isBefore(dataHoraDeEntrada)) {
//						dataHoraMarcacao = dataHoraMarcacao.plusDays(1);
//					}
//				}
//
//				/**
//				 * Verificando as datas, e aqui será levado em consideração o dia....
//				 */
//				if (dataHoraMarcacao.isAfter(dataHoraDeEntrada)) {
//					if (!horaDeAtrasoAntecipadaDetectada) {
//						horaDeAtrasoAntecipadaDetectada = true;
//						horaAtraso = schedule.getEntryHour() + " " + formatIntToHourFormat(markerEntryHour) + ":"
//								+ formatIntToHourFormat(markerEntryMinute);
//
//						System.out.println("Você se atrasou! " + horaAtraso);
//
//						newSummaries.add(new SummaryHour.Builder().setTotalHours(horaAtraso)
//								.setHourType(HourType.LATE.getCod()).build());
//					}
//				} else if (dataHoraMarcacao.isBefore(dataHoraDeEntrada)) {
//					horaExtra = formatIntToHourFormat(markerEntryHour) + ":" + formatIntToHourFormat(markerEntryMinute)
//							+ " " + schedule.getEntryHour();
//
//					System.out.println("Você se antecipou! " + horaExtra);
//
//					newSummaries.add(new SummaryHour.Builder().setTotalHours(horaExtra)
//							.setHourType(HourType.OVERTIME.getCod()).build());
//				}
//
//			} else if (hour < horarioDeEntrada && hour < horarioDeSaida) {
//				/**
//				 * Entrou antes
//				 */
//				if (!horaExtraAntecipadaDetectada) {
//					horaExtraAntecipadaDetectada = true;
//					horaExtra = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(markerEntryMinute);
//				} else if (hour <= markerDepartureHour || hour <= horarioDeEntrada) {
//					/**
//					 * Aqui verificamos se o funcionário não acabou fazendo hora extra e saindo
//					 * antes do horário ou no horário de trabalho
//					 */
//					horaExtra += " " + formatIntToHourFormat(markerDepartureHour) + ":"
//							+ formatIntToHourFormat(markerDepartureMinute);
//
//					System.out.println("Você se antecipou! " + horaExtra);
//
//					newSummaries.add(new SummaryHour.Builder().setTotalHours(horaExtra)
//							.setHourType(HourType.OVERTIME.getCod()).build());
//				}
//			} else if (hour == horarioDeEntrada && hour < horarioDeSaida) {
//				/**
//				 * Entrou no horário
//				 */
//				if (horaExtraAntecipadaDetectada) {
//					/**
//					 * Se tiver encontrado hora extra antesm quer dizer que tinha algo para colocar
//					 * na variável
//					 */
//					if (!horaExtra.isEmpty()) {
//						horaExtra += " " + schedule.getEntryHour();
//
//						System.out.println("Hora extra antes do expediente: " + horaExtra);
//
//						newSummaries.add(new SummaryHour.Builder().setTotalHours(horaExtra)
//								.setHourType(HourType.OVERTIME.getCod()).build());
//					}
//				} else {
//					/**
//					 * Verifica os minutos
//					 */
//					// Hora Extra
//					if (markerEntryMinute < minutoDeEntrada) {
//						if (!horaExtraAntecipadaDetectada) {
//							horaExtra = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(markerEntryMinute)
//									+ " " + schedule.getEntryHour();
//							horaExtraAntecipadaDetectada = true;
//
//							System.out.println("Entrou antecipado! " + horaExtra);
//
//							newSummaries.add(new SummaryHour.Builder().setTotalHours(horaExtra)
//									.setHourType(HourType.OVERTIME.getCod()).build());
//						}
//						// Atraso
//					} else if (markerEntryMinute > minutoDeEntrada) {
//						if (!horaDeAtrasoAntecipadaDetectada) {
//							estaTrabalhando = true;
//							horaAtraso = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(markerEntryMinute)
//									+ " " + schedule.getEntryHour();
//							horaDeAtrasoAntecipadaDetectada = true;
//
//							System.out.println("Entrou atrasado! " + horaAtraso);
//
//							newSummaries.add(new SummaryHour.Builder().setTotalHours(horaAtraso)
//									.setHourType(HourType.LATE.getCod()).build());
//						}
//						// Entrou no horário
//					} else {
//						entrouNoHorario = true;
//						System.out.println("Entrou no horário");
//					}
//				}
//
//			} else if (hour > horarioDeEntrada && hour < horarioDeSaida) {
//				// Se ele encontrou hora extra antes, quer dizer que o funcionário chegou antes
//				// do horário...
//				/**
//				 * Entrou Atrasado
//				 */
//
//				if (hour > horarioDeEntrada && !horaExtraAntecipadaDetectada && !entrouNoHorario) {
//					/**
//					 * Se não, ele entrou atrasado
//					 */
//					if (!horaDeAtrasoAntecipadaDetectada) {
//						horaDeAtrasoAntecipadaDetectada = true;
//						horaAtraso = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(markerEntryMinute);
//						if ((hour + 1) == horarioDeSaida) {
//							/**
//							 * Para casos onde o funcionário fez 1 hora ou menos de trabalho, mas saiu antes
//							 * do horário ainda assim
//							 */
//							horaAtraso += " " + schedule.getEntryHour();
//							System.out.println("Entrou atrasado! " + horaAtraso);
//
//							estaTrabalhando = true;
//
//							newSummaries.add(new SummaryHour.Builder().setTotalHours(horaAtraso)
//									.setHourType(HourType.LATE.getCod()).build());
//						}
//					} else {
//						/*
//						 * Se o funcionário estiver trabalhando não tem por quê calcular horas de
//						 * atraso....
//						 */
//						if (!estaTrabalhando) {
//							horaAtraso += " " + schedule.getEntryHour();
//							estaTrabalhando = true;
//
//							// Após adicionar zera para poder receber mais um horário caso tenha mais de um
//							// atraso no mesmo horário
//							System.out.println("Entrou atrasado: " + horaAtraso);
//							newSummaries.add(new SummaryHour.Builder().setTotalHours(horaAtraso)
//									.setHourType(HourType.LATE.getCod()).build());
//
//						}
//					}
//				} else if (hour == markerDepartureHour) {
//					/*
//					 * Se a hora for igual ao horário de saída marcado, então quer dizer que o
//					 * funcionário saiu, mas ainda deveria estar no expediente dele e será
//					 * considerado um atraso
//					 */
//					horaDeAtrasoAposDetectado = true;
//					horaAtraso = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(markerDepartureMinute) + " "
//							+ schedule.getDepartureTime();
//
//					System.out.println("Saiu mais cedo: " + horaAtraso);
//
//					newSummaries.add(new SummaryHour.Builder().setTotalHours(horaAtraso)
//							.setHourType(HourType.LATE.getCod()).build());
//				}
//
//			} else if (hour > horarioDeEntrada && hour > horarioDeSaida) {
//				/**
//				 * Saiu depois do horário
//				 */
//				if (!horaExtraAposDetectado) {
//					/**
//					 * Pega o início da hora extra
//					 */
//					horaExtraAposDetectado = true;
//					if (hour == horarioDeSaida) {
//						horaExtra = schedule.getDepartureTime();
//					} else {
//						horaExtra = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(markerEntryMinute);
//					}
//				} else {
//					/**
//					 * Fica atualizando até achar o final da hora extra
//					 */
//					if(!estaTrabalhando) {
//						estaTrabalhando = true;
//						horaExtra += " " + formatIntToHourFormat(markerDepartureHour) + ":" + formatIntToHourFormat(markerDepartureMinute);
//
//						System.out.println("Hora extra após o expediente: " + horaExtra);
//
//						newSummaries.add(new SummaryHour.Builder().setTotalHours(horaExtra)
//								.setHourType(HourType.OVERTIME.getCod()).build());	
//					}
//				}
//			}
//		}
//
//		int horarioEsperadoDeSaida = Integer.parseInt(ultimoHorarioProcessado.getDepartureTime().split(":")[0]);
//		if (hour < markerDepartureHour) {
//			/**
//			 * Caso ainda tenham sobrado horas, serão horas extras após o expediente
//			 */
//			horaExtra = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(lastMinute) + " "
//					+ formatIntToHourFormat(markerDepartureHour) + ":" + formatIntToHourFormat(markerDepartureMinute);
//
//			System.out.println("Hora extra após expediente: " + horaExtra);
//
//			newSummaries.add(
//					new SummaryHour.Builder().setTotalHours(horaExtra).setHourType(HourType.OVERTIME.getCod()).build());
//		} else if (markerDepartureHour < horarioEsperadoDeSaida) {
//			/**
//			 * Caso a última hora processada seja menor que o horário de saída, será
//			 * considerado atraso, pois significa que o funcionário saiu antes do horário
//			 */
//			horaAtraso = formatIntToHourFormat(markerDepartureHour) + ":" + formatIntToHourFormat(markerDepartureMinute)
//					+ " " + ultimoHorarioProcessado.getDepartureTime();
//
//			System.out.println("Hora de atraso, funcionário deveria estar trabalhando: " + horaAtraso);
//
//			newSummaries.add(
//					new SummaryHour.Builder().setTotalHours(horaAtraso).setHourType(HourType.LATE.getCod()).build());
//		} else if (markerDepartureMinute > lastMinute) {
//			/**
//			 * Se a hora de saída for igual, então é verificado tamém se o minuto foi
//			 * diferente.... podendo variar para mais ou menos
//			 */
//			horaAtraso = formatIntToHourFormat(markerDepartureHour) + ":" + formatIntToHourFormat(markerDepartureMinute)
//					+ " " + ultimoHorarioProcessado.getDepartureTime();
//
//			System.out.println("Hora Extra, funcionário passou do ponto: " + horaExtra);
//
//			newSummaries.add(new SummaryHour.Builder().setTotalHours(horaAtraso).setHourType(HourType.OVERTIME.getCod())
//					.build());
//		}
//
//		return newSummaries;
//	}
//	
//	private static List<SummaryHour> findOverOrLateHours(List<WorkSchedule> schedules, HourMarker hm) {
//		List<SummaryHour> newSummaries = new ArrayList<>();
//		int markerEntryHour = Integer.parseInt(hm.getEntryHour().split(":")[0]);
//		int markerEntryMinute = Integer.parseInt(hm.getEntryHour().split(":")[1]);
//
//		int markerDepartureHour = Integer.parseInt(hm.getDepartureTime().split(":")[0]);
//		int markerDepartureMinute = Integer.parseInt(hm.getDepartureTime().split(":")[1]);
//
//		// Guarda a hora extra
//		String horaExtra = "";
//
//		// Guarda a hora de atraso
//		String horaAtraso = "";
//
//		// Início da jornada
//		int hour = 0;
//
//		// Guarda o último minuto processado
//		int lastMinute = 0;
//
//		WorkSchedule ultimoHorarioProcessado = null;
//
//		/**
//		 * Caso exista apenas 1 horário cadastrado verifica e calcula em cima dele
//		 */
//		for(WorkSchedule schedule : schedules) {
//			boolean estaTrabalhando = false;
//			boolean entrouNoHorario = false;
//			boolean horaExtraAntecipadaDetectada = false;
//			boolean horaDeAtrasoAntecipadaDetectada = false;
//
//			boolean horaExtraAposDetectado = false;
//			boolean horaDeAtrasoAposDetectado = false;
//
//			int horarioDeEntrada = Integer.parseInt(schedule.getEntryHour().split(":")[0]);
//			int minutoDeEntrada = Integer.parseInt(schedule.getEntryHour().split(":")[1]);
//
//			int horarioDeSaida = Integer.parseInt(schedule.getDepartureTime().split(":")[0]);
//			int minutoDeSaida = Integer.parseInt(schedule.getDepartureTime().split(":")[1]);
//
//			lastMinute = minutoDeSaida;
//
//			/**
//			 * Jornada de Trabalho do funcionário
//			 * 
//			 * Se hour for == 0, então quer dizer que ele iniciou o cálculo agora, mas caso
//			 * já exista um valor, continua de onde parou Casos como uma jornada que passa
//			 * de um turno para o outro por exemplo....
//			 */
//			for (hour = hour == 0 ? markerEntryHour : hour; (hour - 1) < markerDepartureHour; hour++) {
//				ultimoHorarioProcessado = schedule;
//				if (hour == horarioDeSaida) {
//					/**
//					 * Isto marca a chegada ao final do expediente, então passa para o próximo
//					 * horário, caso exista Se não sai fora do loop, e caso ainda existam horas "em
//					 * haver" considera como hora extra
//					 */
//					break;
//				}
//
//				/**
//				 * Se passou o dia, o cálculo vai precisar ser um pouco diferente... Pois
//				 * precisaremos pegar o horário de início, de término e verificar quanto o
//				 * funcionário se atrasou
//				 * 
//				 * Se for maior, quer dizer que passou de um dia para o outro Ex: 22:00 05:00 |
//				 * 19:00 01:00 | 18:00 02:00
//				 */
//				if (horarioDeEntrada > horarioDeSaida) {
//					// Aqui verifica se eu cheguei antes ou depois do horário
//					GregorianCalendar calendar = new GregorianCalendar();
//
//					LocalDateTime dataHoraDeEntrada = LocalDateTime.of(calendar.get(GregorianCalendar.YEAR),
//							calendar.get(GregorianCalendar.MONTH) + 1, calendar.get(GregorianCalendar.DAY_OF_MONTH),
//							horarioDeEntrada, minutoDeEntrada);
//
//					LocalDateTime dataHoraMarcacao = LocalDateTime.of(calendar.get(GregorianCalendar.YEAR),
//							calendar.get(GregorianCalendar.MONTH) + 1, // Iguala
//							calendar.get(GregorianCalendar.DAY_OF_MONTH), markerEntryHour, markerEntryMinute);
//
//					/**
//					 * Como não foi fornecido um campo de data para fazer a comparação se passou de
//					 * um dia para o outro, sempre que for um valor muito menor que a o horário de
//					 * entrada, vou assumir que será um novo dia...
//					 */
//
//					long diferencaEmHoras = Math.abs(ChronoUnit.HOURS.between(dataHoraDeEntrada, dataHoraMarcacao));
//					if (diferencaEmHoras > 6) { // "Permite" até 6 horas de extra....
//						if (dataHoraMarcacao.isBefore(dataHoraDeEntrada)) {
//							dataHoraMarcacao = dataHoraMarcacao.plusDays(1);
//						}
//					}
//
//					/**
//					 * Verificando as datas, e aqui será levado em consideração o dia....
//					 */
//					if (dataHoraMarcacao.isAfter(dataHoraDeEntrada)) {
//						if (!horaDeAtrasoAntecipadaDetectada) {
//							horaDeAtrasoAntecipadaDetectada = true;
//							horaAtraso = schedule.getEntryHour() + " " + formatIntToHourFormat(markerEntryHour) + ":"
//									+ formatIntToHourFormat(markerEntryMinute);
//
//							System.out.println("Você se atrasou! " + horaAtraso);
//
//							newSummaries.add(new SummaryHour.Builder().setTotalHours(horaAtraso)
//									.setHourType(HourType.LATE.getCod()).build());
//						}
//					} else if (dataHoraMarcacao.isBefore(dataHoraDeEntrada)) {
//						horaExtra = formatIntToHourFormat(markerEntryHour) + ":" + formatIntToHourFormat(markerEntryMinute)
//								+ " " + schedule.getEntryHour();
//
//						System.out.println("Você se antecipou! " + horaExtra);
//
//						newSummaries.add(new SummaryHour.Builder().setTotalHours(horaExtra)
//								.setHourType(HourType.OVERTIME.getCod()).build());
//					}
//
//				} else if (hour < horarioDeEntrada && hour < horarioDeSaida) {
//					/**
//					 * Entrou antes
//					 */
//					if (!horaExtraAntecipadaDetectada) {
//						horaExtraAntecipadaDetectada = true;
//						horaExtra = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(markerEntryMinute);
//					} else if (hour <= horarioDeEntrada) {
//						
//						/**
//						 * Aqui verificamos se o funcionário não acabou fazendo hora extra e saindo
//						 * antes do horário ou no horário de trabalho
//						 */
//						horaExtra += " " + formatIntToHourFormat(horarioDeEntrada) + ":"
//								+ formatIntToHourFormat(minutoDeEntrada);
//
//						System.out.println("Você se antecipou! " + horaExtra);
//
//						newSummaries.add(new SummaryHour.Builder().setTotalHours(horaExtra)
//								.setHourType(HourType.OVERTIME.getCod()).build());
//						estaTrabalhando = true;
//						
//					} else if(hour <= markerDepartureHour) {
//						
//						horaExtra += " " + formatIntToHourFormat(markerDepartureHour) + ":"
//								+ formatIntToHourFormat(markerDepartureMinute);
//
//						System.out.println("Você se antecipou! " + horaExtra);
//
//						newSummaries.add(new SummaryHour.Builder().setTotalHours(horaExtra)
//								.setHourType(HourType.OVERTIME.getCod()).build());						
//					}
//				} else if (hour == horarioDeEntrada && hour < horarioDeSaida) {
//					/**
//					 * Entrou no horário
//					 */
//					if (horaExtraAntecipadaDetectada && !estaTrabalhando) {
//						/**
//						 * Se tiver encontrado hora extra antesm quer dizer que tinha algo para colocar
//						 * na variável
//						 */
//						estaTrabalhando = true;
//						if (!horaExtra.isEmpty()) {
//							horaExtra += " " + schedule.getEntryHour();
//
//							System.out.println("Hora extra antes do expediente: " + horaExtra);
//
//							newSummaries.add(new SummaryHour.Builder().setTotalHours(horaExtra)
//									.setHourType(HourType.OVERTIME.getCod()).build());
//						}
//					} else {
//						/**
//						 * Verifica os minutos
//						 */
//						// Hora Extra
//						if (markerEntryMinute < minutoDeEntrada) {
//							if (!horaExtraAntecipadaDetectada) {
//								horaExtra = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(markerEntryMinute)
//										+ " " + schedule.getEntryHour();
//								horaExtraAntecipadaDetectada = true;
//
//								System.out.println("Entrou antecipado! " + horaExtra);
//
//								newSummaries.add(new SummaryHour.Builder().setTotalHours(horaExtra)
//										.setHourType(HourType.OVERTIME.getCod()).build());
//							}
//							// Atraso
//						} else if (markerEntryMinute > minutoDeEntrada) {
//							if (!horaDeAtrasoAntecipadaDetectada) {
//								estaTrabalhando = true;
//								horaAtraso = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(markerEntryMinute)
//										+ " " + schedule.getEntryHour();
//								horaDeAtrasoAntecipadaDetectada = true;
//
//								System.out.println("Entrou atrasado! " + horaAtraso);
//
//								newSummaries.add(new SummaryHour.Builder().setTotalHours(horaAtraso)
//										.setHourType(HourType.LATE.getCod()).build());
//							}
//							// Entrou no horário
//						} else {
//							entrouNoHorario = true;
//							System.out.println("Entrou no horário");
//						}
//					}
//
//				} else if (hour > horarioDeEntrada && hour < horarioDeSaida) {
//					// Se ele encontrou hora extra antes, quer dizer que o funcionário chegou antes
//					// do horário...
//					/**
//					 * Entrou Atrasado
//					 */
//
//					if (hour > horarioDeEntrada && !horaExtraAntecipadaDetectada && !entrouNoHorario) {
//						/**
//						 * Se não, ele entrou atrasado
//						 */
//						if (!horaDeAtrasoAntecipadaDetectada) {
//							horaDeAtrasoAntecipadaDetectada = true;
//							horaAtraso = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(markerEntryMinute);
//							if ((hour + 1) == horarioDeSaida) {
//								/**
//								 * Para casos onde o funcionário fez 1 hora ou menos de trabalho, mas saiu antes
//								 * do horário ainda assim
//								 */
//								horaAtraso += " " + schedule.getEntryHour();
//								System.out.println("Entrou atrasado! " + horaAtraso);
//
//								estaTrabalhando = true;
//
//								newSummaries.add(new SummaryHour.Builder().setTotalHours(horaAtraso)
//										.setHourType(HourType.LATE.getCod()).build());
//							}
//						} else {
//							/*
//							 * Se o funcionário estiver trabalhando não tem por quê calcular horas de
//							 * atraso....
//							 */
//							if (!estaTrabalhando) {
//								horaAtraso += " " + schedule.getEntryHour();
//								estaTrabalhando = true;
//
//								// Após adicionar zera para poder receber mais um horário caso tenha mais de um
//								// atraso no mesmo horário
//								System.out.println("Entrou atrasado: " + horaAtraso);
//								newSummaries.add(new SummaryHour.Builder().setTotalHours(horaAtraso)
//										.setHourType(HourType.LATE.getCod()).build());
//
//							}
//						}
//					} else if (hour == markerDepartureHour) {
//						/*
//						 * Se a hora for igual ao horário de saída marcado, então quer dizer que o
//						 * funcionário saiu, mas ainda deveria estar no expediente dele e será
//						 * considerado um atraso
//						 */
//						horaDeAtrasoAposDetectado = true;
//						horaAtraso = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(markerDepartureMinute) + " "
//								+ schedule.getDepartureTime();
//
//						System.out.println("Saiu mais cedo: " + horaAtraso);
//
//						newSummaries.add(new SummaryHour.Builder().setTotalHours(horaAtraso)
//								.setHourType(HourType.LATE.getCod()).build());
//					}
//
//				} else if (hour > horarioDeEntrada && hour > horarioDeSaida) {
//					/**
//					 * Saiu depois do horário
//					 */
//					if (!horaExtraAposDetectado) {
//						/**
//						 * Pega o início da hora extra
//						 */
//						horaExtraAposDetectado = true;
//						if (hour == horarioDeSaida) {
//							horaExtra = schedule.getDepartureTime();
//						} else {
//							horaExtra = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(markerEntryMinute);
//						}
//					} else {
//						/**
//						 * Fica atualizando até achar o final da hora extra
//						 */
//						if(!estaTrabalhando) {
//							estaTrabalhando = true;
//							horaExtra += " " + formatIntToHourFormat(markerDepartureHour) + ":" + formatIntToHourFormat(markerDepartureMinute);
//
//							System.out.println("Hora extra após o expediente: " + horaExtra);
//
//							newSummaries.add(new SummaryHour.Builder().setTotalHours(horaExtra)
//									.setHourType(HourType.OVERTIME.getCod()).build());	
//						}
//					}
//				}
//			}
//		}
//		
//		int horarioEsperadoDeSaida = Integer.parseInt(ultimoHorarioProcessado.getDepartureTime().split(":")[0]);
//		if (hour < markerDepartureHour) {
//			/**
//			 * Caso ainda tenham sobrado horas, serão horas extras após o expediente
//			 */
//			horaExtra = formatIntToHourFormat(hour) + ":" + formatIntToHourFormat(lastMinute) + " "
//					+ formatIntToHourFormat(markerDepartureHour) + ":" + formatIntToHourFormat(markerDepartureMinute);
//
//			System.out.println("Hora extra após expediente: " + horaExtra);
//
//			newSummaries.add(
//					new SummaryHour.Builder().setTotalHours(horaExtra).setHourType(HourType.OVERTIME.getCod()).build());
//		} else if (markerDepartureHour < horarioEsperadoDeSaida) {
//			/**
//			 * Caso a última hora processada seja menor que o horário de saída, será
//			 * considerado atraso, pois significa que o funcionário saiu antes do horário
//			 */
//			horaAtraso = formatIntToHourFormat(markerDepartureHour) + ":" + formatIntToHourFormat(markerDepartureMinute)
//					+ " " + ultimoHorarioProcessado.getDepartureTime();
//
//			System.out.println("Hora de atraso, funcionário deveria estar trabalhando: " + horaAtraso);
//
//			newSummaries.add(
//					new SummaryHour.Builder().setTotalHours(horaAtraso).setHourType(HourType.LATE.getCod()).build());
//		} else if (markerDepartureMinute > lastMinute) {
//			/**
//			 * Se a hora de saída for igual, então é verificado tamém se o minuto foi
//			 * diferente.... podendo variar para mais ou menos
//			 */
//			horaAtraso = formatIntToHourFormat(markerDepartureHour) + ":" + formatIntToHourFormat(markerDepartureMinute)
//					+ " " + ultimoHorarioProcessado.getDepartureTime();
//
//			System.out.println("Hora Extra, funcionário passou do ponto: " + horaExtra);
//
//			newSummaries.add(new SummaryHour.Builder().setTotalHours(horaAtraso).setHourType(HourType.OVERTIME.getCod())
//					.build());
//		}
//
//		return newSummaries;
//	}
//
//	private static String formatIntToHourFormat(int time) {
//		String formattedHour = "";
//
//		// 9 --> 09
//		if (time == 0) {
//			formattedHour += "00";
//			// 9 -> 09
//		} else if (time < 10) {
//			formattedHour = "0" + time;
//		} else {
//			formattedHour = time + "";
//		}
//
//		return formattedHour;
//	}
//
//	/**
//	 * Compara horários de trabalho, e retorna aquele que o horario seja mais
//	 * próximo do horário de entrada do funcionário..
//	 * 
//	 * @param marker
//	 * @param schedules
//	 * @return
//	 */
//	public static WorkSchedule findNearestSchedule(String marker, List<WorkSchedule> schedules) {
//		int markerInMinutes = convertToMinutes(marker);
//		List<Integer> hourMinutes = new ArrayList<>();
//
//		for (WorkSchedule schedule : schedules) {
//			hourMinutes.add(convertToMinutes(schedule.getEntryHour()));
//		}
//
//		int nearestSchedule = hourMinutes.stream()
//				.min((a, b) -> Integer.compare(Math.abs(a - markerInMinutes), Math.abs(b - markerInMinutes)))
//				.orElseThrow();
//
//		return schedules.stream().filter(schedule -> schedule.getEntryHour().contains(convertToHours(nearestSchedule)))
//				.findFirst().get();
//	}
//
//	public static int convertToMinutes(String hour) {
//		String[] parts = hour.split(":");
//		int hours = Integer.parseInt(parts[0]);
//		int minutes = Integer.parseInt(parts[1]);
//		return hours * 60 + minutes;
//	}
//
//	public static String convertToHours(int minutes) {
//		int hours = minutes / 60;
//		int restOfMinutes = minutes % 60;
//		return String.format("%02d:%02d", hours, restOfMinutes);
//	}
//
//	public static int calcularDiferencaHoras(String horarioInicial, String horarioFinal) {
//		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
//		int diferencaEmHoras = 0;
//		try {
//
//			Date dataInicial = format.parse(horarioInicial);
//			Date dataFinal = format.parse(horarioFinal);
//
//			// Calcula a diferença em milissegundos
//			long diferenca = dataFinal.getTime() - dataInicial.getTime();
//
//			// Converte a diferença de milissegundos para horas
//			diferencaEmHoras = (int) (diferenca / (60 * 60 * 1000));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return diferencaEmHoras;
//	}
//}
